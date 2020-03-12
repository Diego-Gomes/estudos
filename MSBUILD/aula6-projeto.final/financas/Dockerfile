FROM microsoft/dotnet:2.2-sdk
COPY . source
RUN dotnet publish "source/web/FinancasWeb.csproj" -o /app -c Release
ENTRYPOINT ["dotnet", "/app/FinancasWeb.dll"]