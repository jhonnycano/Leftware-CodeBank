
using Microsoft.AspNetCore.SignalR;

namespace Leftware.Examples.SignalR.Utils;

public static class SignalRHelper
{
    internal static async Task SendToClientAsync<T>(Hub hub, string clientId, string message, T content)
    {
        string json = JsonHelper.Serialize(content);
        await hub.Clients.Client(clientId).SendAsync(message, json);
    }
}
