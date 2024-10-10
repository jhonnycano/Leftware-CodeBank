using Leftware.Examples.SignalR.Models;
using Leftware.Examples.SignalR.Utils;
using Microsoft.AspNetCore.SignalR;
using System.Text.Json;

namespace Leftware.Examples.SignalR.Commands;

public sealed class ProcessMoveCommand : IGameCommand
{
    public string CommandName => CommandNames.PROCESS_MOVE;

    public async IAsyncEnumerable<(string, object)> Execute(Hub hub, IGameServerContext context, string argument)
    {
        ProcessMoveRequest? request = JsonHelper.Deserialize<ProcessMoveRequest>(argument) ?? null;
        if (request is null) yield break;

        string connectionId = hub.Context.ConnectionId;
        if (!context.Users.TryGetValue(connectionId, out User? user)) yield break;
        if (!context.Games.TryGetValue(request.GameId, out Game? game)) yield break;
        if (game.GameStatus != GameStatus.InProgress) yield break;

        user.LatestActivity = DateTime.UtcNow;

        JsonDocument gameState = game.ProcessMove(user.Id, request.MoveDetail);
        GameMoveProcessedResponse result = new(gameState, true, "OK");

        string user1 = game.Users.Keys.First();
        string user2 = game.Users.Keys.Skip(1).First();
        
        yield return (user1, result);
        yield return (user2, result);
        await Task.CompletedTask;
    }
}
