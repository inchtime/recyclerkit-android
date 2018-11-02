RecyclerKit for Android
=======================

RecyclerKit is support to use android.support.v7.widget.RecyclerView to display thousands of layout you want via one adapter `RecyclerAdapter`.

RecyclerKit also provide a empty view that you can customized when the recyclerView is empty

Download
--------

Gradle:

```gradle
dependencies {
    implementation 'io.inchtime:recyclerkit:0.3.0'
}
```

Maven:

```xml
<dependency>
  <groupId>io.inchtime</groupId>
  <artifactId>recyclerkit</artifactId>
  <version>0.3.0</version>
  <type>pom</type>
</dependency>
```

How To Use
----------------------

**Kotlin:**

```kotlin
//LinearLayoutManager
val spanCount = 1
val adapter = RecyclerKit.adapter(this, spanCount)
    .recyclerView(R.id.recyclerView)
    .withLinearLayout()
    .build()
```

```kotlin
//GridLayoutManager
val spanCount = 2
val adapter = RecyclerKit.adapter(this, spanCount)
    .recyclerView(R.id.recyclerView)
    .withGridLayout()
    .build()
```

then the RecyclerView will show the empty view

Author
------

Evan Cai

QQ: 214390117

email: evan-cai@live.cn

License
-------

Apache 2.0. See the [LICENSE][2] file for details.

[2]: https://github.com/inchtime/recyclerkit-android/blob/master/LICENSE