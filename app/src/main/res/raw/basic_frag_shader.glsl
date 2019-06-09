//#version 120

precision highp float;

uniform vec4 vColour;
uniform sampler2D uTexture;
varying vec2 vTextureCoordinate;

void main() {
    gl_FragColor = vColour * texture2D(uTexture, vTextureCoordinate);
}
