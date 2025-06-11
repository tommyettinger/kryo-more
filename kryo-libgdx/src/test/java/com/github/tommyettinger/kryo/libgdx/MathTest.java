package com.github.tommyettinger.kryo.libgdx;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Colors;
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
    public void testEllipse() {
        Kryo kryo = new Kryo();
        kryo.register(Ellipse.class, new EllipseSerializer());
        
        Ellipse[] testing = {
                new Ellipse(0, 0, 0, 0),
                new Ellipse(-0f, -0f, -0f, -0f),
                new Ellipse(1, 0, 0, 0),
                new Ellipse(0, 1, 0, 0),
                new Ellipse(0, 0, 1, 0),
                new Ellipse(0, 0, 0, 1),
                new Ellipse(1, 1, 1, 1),
                new Ellipse(-1, -1, -1, -1),
                new Ellipse(9999.9f, 9999.9f, 9999.9f, 9999.9f),
                new Ellipse(9999.9f, -9999.9f, 0, -0f),
                new Ellipse(Float.NaN, Float.NaN, Float.NaN, Float.NaN),
                new Ellipse(Float.POSITIVE_INFINITY, Float.NEGATIVE_INFINITY, Float.NaN, Float.MIN_VALUE),
                new Ellipse(Float.MIN_VALUE, Float.MIN_VALUE, Float.MIN_VALUE, Float.MIN_VALUE),
                new Ellipse(-Float.MIN_VALUE, -Float.MIN_VALUE, -Float.MIN_VALUE, -Float.MIN_VALUE),
                new Ellipse(0x7FF.FFp-5f, 0x7FF.FFp-5f, 0x7FF.FFp-5f, 0x7FF.FFp-5f),
                new Ellipse(-0x7FF.FFp-5f, -0x7FF.FFp-5f, -0x7FF.FFp-5f, -0x7FF.FFp-5f)};

        for (Ellipse data : testing) {
            Output output = new Output(32, -1);
            kryo.writeObject(output, data);
            byte[] bytes = output.toBytes();
            try (Input input = new Input(bytes)) {
                Ellipse data2 = kryo.readObject(input, Ellipse.class);
                // Ellipse does not implement equals().
//            Assert.assertEquals(data, data2);
                Assert.assertEquals(data.x, data2.x, 0.00001f);
                Assert.assertEquals(data.y, data2.y, 0.00001f);
                Assert.assertEquals(data.width, data2.width, 0.00001f);
                Assert.assertEquals(data.height, data2.height, 0.00001f);
            }
        }
    }

    @Test
    public void testGridPoint2() {
        Kryo kryo = new Kryo();
        kryo.register(GridPoint2.class, new GridPoint2Serializer());

        GridPoint2[] testing = {new GridPoint2(0, 0), new GridPoint2(1, 0), new GridPoint2(0, 1),
                new GridPoint2(-1, -1), new GridPoint2(9999, 9999), new GridPoint2(9999, -9999),
                new GridPoint2(0x7FFFFFFF, 0x7FFFFFFF), new GridPoint2(0x80000000, 0x80000000)};

        for (GridPoint2 data : testing) {
            Output output = new Output(32, -1);
            kryo.writeObject(output, data);
            byte[] bytes = output.toBytes();
            try (Input input = new Input(bytes)) {
                GridPoint2 data2 = kryo.readObject(input, GridPoint2.class);
                Assert.assertEquals(data, data2);
            }
        }
    }

    @Test
    public void testGridPoint3() {
        Kryo kryo = new Kryo();
        kryo.register(GridPoint3.class, new GridPoint3Serializer());

        GridPoint3[] testing = {new GridPoint3(0, 0, 0), new GridPoint3(1, 0, 0), new GridPoint3(0, 1, 0),
                new GridPoint3(0, 0, 1), new GridPoint3(1, 1, 1),
                new GridPoint3(-1, -1, -1), new GridPoint3(9999, 9999, 9999), new GridPoint3(9999, -9999, 0),
                new GridPoint3(0x7FFFFFFF, 0x7FFFFFFF, 0x7FFFFFFF), new GridPoint3(0x80000000, 0x80000000, 0x80000000)};

        for (GridPoint3 data : testing) {
            Output output = new Output(32, -1);
            kryo.writeObject(output, data);
            byte[] bytes = output.toBytes();
            try (Input input = new Input(bytes)) {
                GridPoint3 data2 = kryo.readObject(input, GridPoint3.class);
                Assert.assertEquals(data, data2);
            }
        }
    }

    @Test
    public void testMatrix3() {
        Kryo kryo = new Kryo();
        kryo.register(Matrix3.class, new Matrix3Serializer());

        Matrix3 data = new Matrix3().scale(2.1f, 3.3f).rotateRad(2f);

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            Matrix3 data2 = kryo.readObject(input, Matrix3.class);
            // Matrix3 does not implement equals().
//            Assert.assertEquals(data, data2);
            Assert.assertArrayEquals(data.val, data2.val, 0.00001f);
        }
    }

    @Test
    public void testMatrix4() {
        Kryo kryo = new Kryo();
        kryo.register(Matrix4.class, new Matrix4Serializer());

        Matrix4 data = new Matrix4().scale(2.1f, 3.3f, 4.6f).rotateRad(-1.1f, -2.2f, -3.3f, 99.9f);

        Output output = new Output(32, -1);
        kryo.writeObject(output, data);
        byte[] bytes = output.toBytes();
        try (Input input = new Input(bytes)) {
            Matrix4 data2 = kryo.readObject(input, Matrix4.class);
            // Matrix4 does not implement equals().
//            Assert.assertEquals(data, data2);
            Assert.assertArrayEquals(data.val, data2.val, 0.00001f);
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

    @Test
    public void testColor() {
        Kryo kryo = new Kryo();
        kryo.register(Color.class, new ColorSerializer());

        for (Color data : Colors.getColors().values()) {
            Output output = new Output(32, -1);
            kryo.writeObject(output, data);
            byte[] bytes = output.toBytes();
            try (Input input = new Input(bytes)) {
                Color data2 = kryo.readObject(input, Color.class);
                Assert.assertEquals(data, data2);
            }
        }
    }
}
