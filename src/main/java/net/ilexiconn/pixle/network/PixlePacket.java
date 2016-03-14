package net.ilexiconn.pixle.network;

import com.esotericsoftware.kryonet.Connection;
import net.ilexiconn.pixle.client.PixleClient;
import net.ilexiconn.pixle.entity.PlayerEntity;
import net.ilexiconn.pixle.server.PixleServer;

public abstract class PixlePacket {
    public void handleServer(Connection connection) {
        PixleServer instance = PixleServer.INSTANCE;
        handleServer(instance, instance.getLevel().getPlayerByUsername(PixleNetworkManager.getUsername(connection)), connection, getEstimatedSendTime(connection));
    }

    public void handleClient(Connection connection) {
        handleClient(PixleClient.INSTANCE, connection, getEstimatedSendTime(connection));
    }

    private long getEstimatedSendTime(Connection connection) {
        return System.currentTimeMillis() - (connection.getReturnTripTime() / 2);
    }

    public abstract void handleServer(PixleServer pixleServer, PlayerEntity player, Connection connection, long estimatedSendTime);

    public abstract void handleClient(PixleClient client, Connection connection, long estimatedSendTime);
}
