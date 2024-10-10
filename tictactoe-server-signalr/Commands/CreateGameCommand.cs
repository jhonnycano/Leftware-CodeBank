using Leftware.Examples.SignalR.Models;
using Leftware.Examples.SignalR.Utils;
using Microsoft.AspNetCore.SignalR;

namespace Leftware.Examples.SignalR.Commands;

public sealed class CreateGameCommand : IGameCommand
{
    public string CommandName => CommandNames.CREATE_GAME;

    public async IAsyncEnumerable<(string, object)> Execute(Hub hub, IGameServerContext context, string argument)
    {
        CreateGameRequest? request = JsonHelper.Deserialize<CreateGameRequest>(argument) ?? null;
        if (request is null) yield break;

        string connectionId = hub.Context.ConnectionId;
        if (!context.Users.TryGetValue(connectionId, out User? user)) yield break;

        user.LatestActivity = DateTime.UtcNow;

        Game game = new(request.GameType, user);
        context.Games[game.Id] = game;

        GameCreatedResponse result = new(game.Id, true, "OK");
        yield return (user.Id, result);
        await Task.CompletedTask;
    }
}
