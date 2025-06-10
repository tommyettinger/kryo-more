package com.github.tommyettinger.kryo.libgdx;

import com.badlogic.gdx.math.*;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import org.junit.Assert;
import org.junit.Test;

public class MathTest {
    @Test
    public void testAffine2() {
        Kryo kryo = new Kryo();
        kryo.register(Affine2.class, new Affine2Serializer());

        Affine2 data = new Affine2();

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            Affine2 data2 = kryo.readObject(input, Affine2.class);
            // Affine2 does not implement equals().
//            Assert.assertEquals(data, data2);
            Assert.assertEquals(data.m00, data2.m00, 0.00001f);
            Assert.assertEquals(data.m01, data2.m01, 0.00001f);
            Assert.assertEquals(data.m02, data2.m02, 0.00001f);
            Assert.assertEquals(data.m10, data2.m10, 0.00001f);
            Assert.assertEquals(data.m11, data2.m11, 0.00001f);
            Assert.assertEquals(data.m12, data2.m12, 0.00001f);
        }
    }

    @Test
    public void testBoundingBox() {
        Kryo kryo = new Kryo();
        kryo.register(BoundingBox.class, new BoundingBoxSerializer());

        Vector3[] testing = {
                new Vector3(0, 0, 0),
                new Vector3(-0f, -0f, -0f),
                new Vector3(1, 0, 0),
                new Vector3(0, 1, 0),
                new Vector3(0, 0, 1),
                new Vector3(1, 1, 1),
                new Vector3(-1, -1, -1),
                new Vector3(9999.9f, 9999.9f, 9999.9f),
                new Vector3(9999.9f, -9999.9f, 0),
                new Vector3(Float.NaN, Float.NaN, Float.NaN),
                new Vector3(Float.POSITIVE_INFINITY, Float.NEGATIVE_INFINITY, Float.NaN),
                new Vector3(Float.MIN_VALUE, Float.MIN_VALUE, Float.MIN_VALUE),
                new Vector3(-Float.MIN_VALUE, -Float.MIN_VALUE, -Float.MIN_VALUE),
                new Vector3(0x7FF.FFp-5f, 0x7FF.FFp-5f, 0x7FF.FFp-5f), new Vector3(-0x7FF.FFp-5f, -0x7FF.FFp-5f, -0x7FF.FFp-5f)};

        for (Vector3 origin : testing) {
            for (Vector3 direction : testing) {
                BoundingBox data = new BoundingBox(origin, direction);
                Output output = new Output(32, -1);
                kryo.writeObject(output, data);
                byte[] bytes = output.toBytes();
                try (Input input = new Input(bytes)) {
                    BoundingBox data2 = kryo.readObject(input, BoundingBox.class);
                    // BoundingBox does not implement equals().
//                    Assert.assertEquals(data, data2);
                    Assert.assertEquals(data.min, data2.min);
                    Assert.assertEquals(data.max, data2.max);
                }
            }
        }
    }

    @Test
    public void testCircle() {
        Kryo kryo = new Kryo();
        kryo.register(Circle.class, new CircleSerializer());

        Circle[] testing = {
                new Circle(0, 0, 0),
                new Circle(-0f, -0f, -0f),
                new Circle(1, 0, 0),
                new Circle(0, 1, 0),
                new Circle(0, 0, 1),
                new Circle(1, 1, 1),
                new Circle(-1, -1, -1),
                new Circle(9999.9f, 9999.9f, 9999.9f),
                new Circle(9999.9f, -9999.9f, 0),
                new Circle(Float.POSITIVE_INFINITY, Float.NEGATIVE_INFINITY, Float.MIN_VALUE),
                new Circle(Float.MIN_VALUE, Float.MIN_VALUE, Float.MIN_VALUE),
                new Circle(-Float.MIN_VALUE, -Float.MIN_VALUE, -Float.MIN_VALUE),
                new Circle(0x7FF.FFp-5f, 0x7FF.FFp-5f, 0x7FF.FFp-5f), new Circle(-0x7FF.FFp-5f, -0x7FF.FFp-5f, -0x7FF.FFp-5f)};

        for (Circle data : testing) {
            Output output = new Output(32, -1);
            kryo.writeObject(output, data);
            byte[] bytes = output.toBytes();
            try (Input input = new Input(bytes)) {
                Circle data2 = kryo.readObject(input, Circle.class);
                Assert.assertEquals(data, data2);
            }
        }
    }

    @Test
    public void testRandomXS128() {
        Kryo kryo = new Kryo();
        kryo.register(RandomXS128.class, new RandomXS128Serializer());

        RandomXS128 data = new RandomXS128(-12345L);

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            RandomXS128 data2 = kryo.readObject(input, RandomXS128.class);
            Assert.assertEquals(data.nextInt(), data2.nextInt());
            Assert.assertEquals(data.nextLong(), data2.nextLong());
            // RandomXS128 does not implement equals().
//        Assert.assertEquals(data, data2);
            Assert.assertEquals(data.getState(0), data2.getState(0));
            Assert.assertEquals(data.getState(1), data2.getState(1));
        }
    }

    @Test
    public void testVector2() {
        Kryo kryo = new Kryo();
        kryo.register(Vector2.class, new Vector2Serializer());

        Vector2[] testing = {new Vector2(0, 0), new Vector2(-0f, -0f), new Vector2(1, 0), new Vector2(0, 1),
                new Vector2(-1, -1), new Vector2(9999.9f, 9999.9f), new Vector2(9999.9f, -9999.9f),
                new Vector2(Float.NaN, Float.NaN), new Vector2(Float.POSITIVE_INFINITY, Float.NEGATIVE_INFINITY),
                new Vector2(Float.MIN_VALUE, Float.MIN_VALUE), new Vector2(-Float.MIN_VALUE, -Float.MIN_VALUE),
                new Vector2(0x7FF.FFp-5f, 0x7FF.FFp-5f), new Vector2(-0x7FF.FFp-5f, -0x7FF.FFp-5f)};

        for (Vector2 data : testing) {
            Output output = new Output(32, -1);
            kryo.writeObject(output, data);
            byte[] bytes = output.toBytes();
            try (Input input = new Input(bytes)) {
                Vector2 data2 = kryo.readObject(input, Vector2.class);
                Assert.assertEquals(data, data2);
            }
        }
    }

    @Test
    public void testVector3() {
        Kryo kryo = new Kryo();
        kryo.register(Vector3.class, new Vector3Serializer());

        Vector3[] testing = {
                new Vector3(0, 0, 0),
                new Vector3(-0f, -0f, -0f),
                new Vector3(1, 0, 0),
                new Vector3(0, 1, 0),
                new Vector3(0, 0, 1),
                new Vector3(1, 1, 1),
                new Vector3(-1, -1, -1),
                new Vector3(9999.9f, 9999.9f, 9999.9f),
                new Vector3(9999.9f, -9999.9f, 0),
                new Vector3(Float.NaN, Float.NaN, Float.NaN),
                new Vector3(Float.POSITIVE_INFINITY, Float.NEGATIVE_INFINITY, Float.NaN),
                new Vector3(Float.MIN_VALUE, Float.MIN_VALUE, Float.MIN_VALUE),
                new Vector3(-Float.MIN_VALUE, -Float.MIN_VALUE, -Float.MIN_VALUE),
                new Vector3(0x7FF.FFp-5f, 0x7FF.FFp-5f, 0x7FF.FFp-5f), new Vector3(-0x7FF.FFp-5f, -0x7FF.FFp-5f, -0x7FF.FFp-5f)};

        for (Vector3 data : testing) {
            Output output = new Output(32, -1);
            kryo.writeObject(output, data);
            byte[] bytes = output.toBytes();
            try (Input input = new Input(bytes)) {
                Vector3 data2 = kryo.readObject(input, Vector3.class);
                Assert.assertEquals(data, data2);
            }
        }
    }

    @Test
    public void testVector4() {
        Kryo kryo = new Kryo();
        kryo.register(Vector4.class, new Vector4Serializer());

        Vector4[] testing = {
                new Vector4(0, 0, 0, 0),
                new Vector4(-0f, -0f, -0f, -0f),
                new Vector4(1, 0, 0, 0),
                new Vector4(0, 1, 0, 0),
                new Vector4(0, 0, 1, 0),
                new Vector4(0, 0, 0, 1),
                new Vector4(1, 1, 1, 1),
                new Vector4(-1, -1, -1, -1),
                new Vector4(9999.9f, 9999.9f, 9999.9f, 9999.9f),
                new Vector4(9999.9f, -9999.9f, 0, -0f),
                new Vector4(Float.NaN, Float.NaN, Float.NaN, Float.NaN),
                new Vector4(Float.POSITIVE_INFINITY, Float.NEGATIVE_INFINITY, Float.NaN, Float.MIN_VALUE),
                new Vector4(Float.MIN_VALUE, Float.MIN_VALUE, Float.MIN_VALUE, Float.MIN_VALUE),
                new Vector4(-Float.MIN_VALUE, -Float.MIN_VALUE, -Float.MIN_VALUE, -Float.MIN_VALUE),
                new Vector4(0x7FF.FFp-5f, 0x7FF.FFp-5f, 0x7FF.FFp-5f, 0x7FF.FFp-5f),
                new Vector4(-0x7FF.FFp-5f, -0x7FF.FFp-5f, -0x7FF.FFp-5f, -0x7FF.FFp-5f)};

        for (Vector4 data : testing) {
            Output output = new Output(32, -1);
            kryo.writeObject(output, data);
            byte[] bytes = output.toBytes();
            try (Input input = new Input(bytes)) {
                Vector4 data2 = kryo.readObject(input, Vector4.class);
                Assert.assertEquals(data, data2);
            }
        }
    }
}
