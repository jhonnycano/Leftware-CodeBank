using Leftware.Examples.SignalR.Models;
using Leftware.Examples.SignalR.Utils;
using Microsoft.AspNetCore.SignalR;

namespace Leftware.Examples.SignalR.Commands;

public sealed class AddUserGameCommand : IGameCommand
{
    public string CommandName => CommandNames.ADD_USER;

    public async IAsyncEnumerable<(string, object)> Execute(Hub hub, IGameServerContext context, string argument)
    {
        AddUserRequest? request = JsonHelper.Deserialize<AddUserRequest>(argument) ?? null;
        if (request is null) yield break;

        string connectionId = hub.Context.ConnectionId;
        if (context.Users.ContainsKey(connectionId)) yield break;

        User user = new(connectionId, request.Username, DateTime.UtcNow);
        context.Users[connectionId] = user;

        UserAddedResponse result = new(true, "OK");
        yield return (user.Id, result);
        await Task.CompletedTask;
    }
}
