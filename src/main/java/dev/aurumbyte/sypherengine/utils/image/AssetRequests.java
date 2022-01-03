package dev.aurumbyte.sypherengine.utils.image;

import dev.aurumbyte.sypherengine.utils.light.Light;

public enum AssetRequests {;
    public static class ImageRequest {
        public Image image;
        public int zDepth;
        public int offX, offY;

        public ImageRequest(Image image, int zDepth, int offX, int offY) {
            this.image = image;
            this.zDepth = zDepth;
            this.offX = offX;
            this.offY = offY;
        }
    }

    public static class LightRequest {
        public Light light;
        public int xPos, yPos;

        public LightRequest(Light light, int xPos, int yPos){
            this.light = light;
            this.xPos = xPos;
            this.yPos = yPos;
        }
    }
}
