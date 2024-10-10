import React, { createContext, useState, useEffect } from 'react';
import { HubConnection, HubConnectionBuilder } from '@microsoft/signalr';

interface ConnectionContextValue {
    connection: HubConnection | null;
}

export const ConnectionContext = createContext<ConnectionContextValue>({
    connection: null,
});

export function ConnectionProvider({ children }: { children: React.ReactNode }) {
    const [connection, setConnection] = useState<HubConnection | null>(null);

    useEffect(() => {
        const newConnection = new HubConnectionBuilder()
            .withUrl('/tictactoe')
            .build();

        newConnection.on('receiveMessage', (msg: string) => {
            console.log(`Received message ${msg}`);
            const message = JSON.parse(msg);
            const messageType = message.messageType;
            delete message.messageType;
            switch (messageType) {
                case "UserAdded":
                    // onUserAdded(message);
                    break;
                case "GameListRetrieved":
                    // onGameListRetrieved(message);
                    break;
                case "GameCreated":
                    // onGameCreated(message);
                    break;
                case "GameStarted":
                    // onGameStarted(message);
                    break;
                case "GameMoveProcessed":
                    // onGameMoveProcessed(message);
                    break;
            }
        });

        newConnection.start()
            .then(() => {
                setConnection(newConnection);
            })
            .catch(error => {
                console.error(error);
            });

        return () => {
            connection?.stop();
        };
    }, []);

    return (
        <ConnectionContext.Provider value={{ connection }}>
            {children}
        </ConnectionContext.Provider>
    );
}