package org.pixle.network;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.minlog.Log;
import org.pixle.client.PixleClient;
import org.pixle.entity.Entity;
import org.pixle.entity.PlayerEntity;
import org.pixle.level.Level;
import org.pixle.server.PixleServer;

public class ConnectPacket extends PixlePacket {
    private String username;

    public ConnectPacket() {
    }

    public ConnectPacket(String username) {
        this.username = username;
    }

    @Override
    public void handleServer(PixleServer pixleServer, PlayerEntity player, Connection connection, long estimatedSendTime) {
        PixleServer instance = PixleServer.INSTANCE;
        int connectionId = connection.getID();
        Server server = (Server) connection.getEndPoint();
        if (PixleNetworkManager.addClient(connection, username)) {
            Log.info("Server", username + " has connected to the server!");
            Level level = instance.getLevel();
            player = new PlayerEntity(level, username);
            player.posY = level.getGenerationHeight((int) player.posX) + 1;
            level.addEntity(player, true);
            server.sendToTCP(connectionId, new SetPlayerPacket(player));
            for (Entity entity : level.getEntities()) {
                server.sendToTCP(connectionId, new AddEntityPacket(entity));
            }
        } else {
            Log.info("Server", username + " tried to join but somebody with that username is connected!");
            connection.close();
        }
    }

    @Override
    public void handleClient(PixleClient client, Connection connection, long estimatedSendTime) {

    }
}
