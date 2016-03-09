package net.ilexiconn.pixle;

public class PixleClient {
    private boolean closeRequested;

    public void start() {
        startTick();
    }

    private void startTick() {
        double delta = 0;
        long previousTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        int ups = 0;

        while (!closeRequested) {
            long currentTime = System.nanoTime();
            delta += (currentTime - previousTime) / 10000000.0;
            previousTime = currentTime;

            while(delta >= 1) {
                tick();

                delta--;
                ups++;
            }

            if(System.currentTimeMillis() - timer > 1000) {
                System.out.println("UPS: " + ups);

                timer += 1000;
                ups = 0;
            }
        }
    }

    private void tick() {
    }

    private void renderTick() {
        while (!closeRequested) {

        }
    }
}
