using Leftware.Examples.SignalR.Commands;
using Leftware.Examples.SignalR.Models;
using Leftware.Examples.SignalR.Utils;
using Microsoft.AspNetCore.SignalR;

namespace Leftware.Examples.SignalR;

public sealed class GameHub : Hub
{
    private readonly IGameServerContext _gameServerContext;
    private readonly IServiceProvider _serviceProvider;

    public GameHub(
        IGameServerContext gameServerContext,
        IServiceProvider serviceProvider
        )
    {
        _gameServerContext = gameServerContext;
        _serviceProvider = serviceProvider;
    }

    public async Task ExecuteCommand(string command, string argument)
    {
        IGameCommand? gameCommand = _serviceProvider.GetKeyedService<IGameCommand>(command);
        if (gameCommand is null)
        {
            return;
        }

        await foreach ((string user, object response) in gameCommand.Execute(this, _gameServerContext, argument))
        {
            await SignalRHelper.SendToClientAsync(this, user, "SendMessage", response);
        }
    }

    public override Task OnDisconnectedAsync(Exception? exception)
    {
        if (_gameServerContext.Users.ContainsKey(Context.ConnectionId))
        {
            _gameServerContext.Users.Remove(Context.ConnectionId);
        }

        IEnumerable<Game> gamesWithoutUser = _gameServerContext.Games
            .Where(g => g.Value.Users.Keys.Any(u => !_gameServerContext.Users.ContainsKey(u)))
            .Select(g => g.Value)
            .ToList();
        foreach (Game game in gamesWithoutUser)
        {
            game.GameStatus = GameStatus.Aborted;
        }

        return base.OnDisconnectedAsync(exception);
    }
}
