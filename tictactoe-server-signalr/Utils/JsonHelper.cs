using System.Text.Json;

namespace Leftware.Examples.SignalR.Utils;

public static class JsonHelper
{
    private static JsonSerializerOptions _options = new JsonSerializerOptions
    {
        AllowTrailingCommas = true,
        PropertyNameCaseInsensitive = true,
        PropertyNamingPolicy = JsonNamingPolicy.CamelCase,
    };

    public static T? Deserialize<T>(string json)
    {
        T? result = JsonSerializer.Deserialize<T>(json, _options);
        return result;
    }

    public static string Serialize<T>(T input)
    {
        string result = JsonSerializer.Serialize<T>(input, _options);
        return result;
    }
}
