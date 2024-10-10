using Leftware.Examples.SignalR.Models;
using Leftware.Examples.SignalR.Utils;
using Microsoft.AspNetCore.SignalR;

namespace Leftware.Examples.SignalR.Commands;

public sealed class JoinGameCommand : IGameCommand
{
    public string CommandName => CommandNames.JOIN_GAME;

    public async IAsyncEnumerable<(string, object)> Execute(Hub hub, IGameServerContext context, string argument)
    {
        JoinGameRequest? request = JsonHelper.Deserialize<JoinGameRequest>(argument) ?? null;
        if (request is null) yield break;

        string connectionId = hub.Context.ConnectionId;
        if (!context.Users.TryGetValue(connectionId, out User? userJoining)) yield break;
        if (!context.Games.TryGetValue(request.GameId, out Game? game)) yield break;
        if (game.GameStatus != GameStatus.New) yield break;
        if (!context.Users.TryGetValue(game.UserOwner.Id, out User? userOwner)) yield break;

        userOwner.LatestActivity = DateTime.UtcNow;
        userJoining.LatestActivity = DateTime.UtcNow;
        if (userOwner.Id == userJoining.Id) yield break;

        game.Users[userJoining.Id] = userJoining;

        game.Initialize();

        GameStartedResponse message1 = new(game.Id, game.Turn ?? "", game.UserGameInfo[userOwner.Id], true, "OK");
        GameStartedResponse message2 = new(game.Id, game.Turn ?? "", game.UserGameInfo[userJoining.Id], true, "OK");

        yield return (userOwner.Id, message1);
        yield return (userJoining.Id, message2);
        await Task.CompletedTask;
    }
}
