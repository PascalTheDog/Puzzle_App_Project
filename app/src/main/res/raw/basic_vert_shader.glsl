//#version 120

uniform mat4 uMVPMatrix;
attribute vec4 vPosition;
attribute vec2 aTextureCoordinate;
varying vec2 vTextureCoordinate;

void main() {
    vTextureCoordinate = aTextureCoordinate;

    gl_Position = uMVPMatrix * vPosition;
}
