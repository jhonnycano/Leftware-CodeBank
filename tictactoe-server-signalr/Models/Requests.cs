using System.Text.Json;

namespace Leftware.Examples.SignalR.Models;

public sealed record AddUserRequest(string Username);

public sealed record GetGameListRequest(string Filter);

public sealed record CreateGameRequest(GameType GameType);

public sealed record JoinGameRequest(string GameId);

public sealed record ProcessMoveRequest(string GameId, JsonDocument MoveDetail);
