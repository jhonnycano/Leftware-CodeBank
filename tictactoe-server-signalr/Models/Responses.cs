using Leftware.Examples.SignalR.Commands;
using System.Text.Json;

namespace Leftware.Examples.SignalR.Models;

public record BaseResponse(string MessageType, bool Success, string Message);

public sealed record UserAddedResponse(bool Success, string Message) : BaseResponse(ResponseNames.USER_ADDED, Success, Message);

public sealed record GameCreatedResponse(string GameId, bool Success, string Message) : BaseResponse(ResponseNames.GAME_CREATED, Success, Message);

public sealed record GameListRetrievedResponse(IList<GameListItem> Data, bool Success, string Message) : BaseResponse(ResponseNames.GAME_LIST_RETRIEVED, Success, Message);

public sealed record GameStartedResponse(string GameId, string Turn, string Avatar, bool Success, string Message) : BaseResponse(ResponseNames.GAME_STARTED, Success, Message);

public sealed record GameMoveProcessedResponse(JsonDocument MoveInfo, bool Success, string Message) : BaseResponse(ResponseNames.GAME_MOVE_PROCESSED, Success, Message);


public sealed record GameListItem(string Id, string Type, string User);
public sealed record TicTacToeSimpleMoveInfo(string Result, string? Turn, string? WinnerId, string? WinnerName, string[] Board);

