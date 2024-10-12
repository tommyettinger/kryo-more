# kryo-more

Kryo 5.x serializers for various libGDX-related libraries.

This lets [Kryo](https://github.com/EsotericSoftware/kryo) 5.x (currently 5.6.2)
de/serialize objects from [RegExodus](https://github.com/tommyettinger/RegExodus),
[digital](https://github.com/tommyettinger/digital), [jdkgdxds](https://github.com/tommyettinger/jdkgdxds),
[juniper](https://github.com/tommyettinger/juniper), [cringe](https://github.com/tommyettinger/cringe),
[gand](https://github.com/tommyettinger/gand),
and [simple-graphs](https://github.com/earlygrey/simple-graphs).
All of these but simple-graphs are maintained by [tommyettinger](https://github.com/tommyettinger), who also maintains
this serialization library.

You may want to consider using [Fury](https://fury.apache.org) serialization instead of Kryo, since it
sometimes doesn't need these kinds of manual serializer, and is faster than Kryo in most benchmarks. It isn't
as mature of a project, but it's being incubated by the Apache Foundation, and is developing quickly. If you
do use Fury, you can get manual serializers (when they are needed) from kryo-more's relative project,
[tantrum](https://github.com/tommyettinger/tantrum).

## How to get

Each sub-library has its own version, linked to the version of the library it de/serializes.
The last component of the version is usually .0, but can be increased for bug-fixes to the same linked library version,
or if Kryo itself had a (compatible) update available but the linked library did not have an update.

  - kryo-regexodus is currently version 0.1.16.1
  - kryo-digital is currently version 0.5.2.1
  - kryo-jdkgdxds is currently version 1.6.5.1
  - kryo-juniper is currently version 0.6.1.1
  - kryo-cringe is currently version 0.1.1.2
  - kryo-simple-graphs is currently (usable at) version 3.0.0.1
    - This depends on simple-graphs v3.0.0, not the current 5.1.1, because versions since 4.0.0 don't make the edges on
      graphs serializable in any way (they use a lambda per-edge).
  - kryo-gand is at 0.1.1.1
    - This release has a known bug that seems to be in Kryo itself; `PointI3` is unusable for serialization with Kryo.

All of these sub-libraries depend on Java 8 or higher, except for kryo-regexodus (which depends on Java 7 or higher) and
some older versions of kryo-digital and kryo-juniper.

Gradle dependency info:

kryo-regexodus:

```gradle
implementation "com.github.tommyettinger:kryo-regexodus:0.1.16.1"
```

kryo-digital:

```gradle
implementation "com.github.tommyettinger:kryo-digital:0.5.2.1"
```

kryo-jdkgdxds:

```gradle
implementation "com.github.tommyettinger:kryo-jdkgdxds:`1.6.5.1`"
```

kryo-juniper:

```gradle
implementation "com.github.tommyettinger:kryo-juniper:0.6.1.1"
```

kryo-cringe:

```gradle
implementation "com.github.tommyettinger:kryo-cringe:0.1.1.2"
```

kryo-gand:

```gradle
implementation "com.github.tommyettinger:kryo-gand:0.1.1.1"
```

kryo-simple-graphs:

```gradle
implementation "com.github.tommyettinger:kryo-simple-graphs:3.0.0.1"
```

Maven dependency info:

kryo-regexodus:

```xml
<dependency>
  <groupId>com.github.tommyettinger</groupId>
  <artifactId>kryo-regexodus</artifactId>
  <version>0.1.16.1</version>
</dependency>
```

kryo-digital:

```xml
<dependency>
  <groupId>com.github.tommyettinger</groupId>
  <artifactId>kryo-digital</artifactId>
  <version>0.5.2.1</version>
</dependency>
```

kryo-jdkgdxds:

```xml
<dependency>
  <groupId>com.github.tommyettinger</groupId>
  <artifactId>kryo-jdkgdxds</artifactId>
  <version>1.6.5.1</version>
</dependency>
```

kryo-juniper:

```xml
<dependency>
  <groupId>com.github.tommyettinger</groupId>
  <artifactId>kryo-juniper</artifactId>
  <version>0.6.1.1</version>
</dependency>
```

kryo-cringe:

```xml
<dependency>
  <groupId>com.github.tommyettinger</groupId>
  <artifactId>kryo-cringe</artifactId>
  <version>0.1.1.2</version>
</dependency>
```

kryo-gand:

```xml
<dependency>
  <groupId>com.github.tommyettinger</groupId>
  <artifactId>kryo-gand</artifactId>
  <version>0.1.1.1</version>
</dependency>
```

kryo-simple-graphs:

```xml
<dependency>
  <groupId>com.github.tommyettinger</groupId>
  <artifactId>kryo-simple-graphs</artifactId>
  <version>3.0.0.1</version>
</dependency>
```

### GWT

GWT is not supported because Kryo doesn't support it. You can use libGDX Json on GWT; except for simple-graphs, cringe,
and gand, all the libraries here are supported by [jdkgdxds-interop](https://github.com/tommyettinger/jdkgdxds_interop) with Json. You can just use cringe and gand
with libGDX Json natively, since they have classes that implement `Json.Serializable`.

## License

Apache 2.0, [see the LICENSE file](LICENSE).