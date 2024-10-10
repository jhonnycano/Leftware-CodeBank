using Leftware.Examples.SignalR.Models;
using Leftware.Examples.SignalR.Utils;
using Microsoft.AspNetCore.SignalR;

namespace Leftware.Examples.SignalR.Commands;

public sealed class GetGameListCommand : IGameCommand
{
    public string CommandName => CommandNames.GET_GAME_LIST;

    public async IAsyncEnumerable<(string, object)> Execute(Hub hub, IGameServerContext context, string argument)
    {
        GetGameListRequest? request = JsonHelper.Deserialize<GetGameListRequest>(argument) ?? null;
        if (request is null) yield break;

        string connectionId = hub.Context.ConnectionId;
        if (!context.Users.TryGetValue(connectionId, out User? user)) yield break;

        user.LatestActivity = DateTime.UtcNow;

        IList<GameListItem> list = context.Games.Values
            .Where(g => g.GameStatus == GameStatus.New && g.UserOwner.Id != user.Id)
            .Select(g => new GameListItem(g.Id, g.GameType.ToString(), g.UserOwner.Name))
            .ToList();

        GameListRetrievedResponse result = new(list, true, "OK");
        yield return (user.Id, result);
        await Task.CompletedTask;
    }
}
