RecyclerKit for Android
=======================

[ ![Download](https://api.bintray.com/packages/inchtime/maven/recyclerkit/images/download.svg) ](https://bintray.com/inchtime/maven/recyclerkit/_latestVersion) [![License](https://img.shields.io/badge/license-Apache%202-blue.svg)](https://www.apache.org/licenses/LICENSE-2.0) [![Join the chat at https://gitter.im/inchtime/recyclerkit-android](https://badges.gitter.im/inchtime/recyclerkit-android.svg)](https://gitter.im/inchtime/recyclerkit-android?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

RecyclerKit is support to use androidx.recyclerview.widget.RecyclerView to display thousands of layout you want via one adapter `RecyclerAdapter`.

RecyclerKit also provide a empty view that you can customized when the recyclerView is empty

Screenshot
----------

![AppStore](https://github.com/inchtime/recyclerkit-android/blob/master/recourse/appstore.gif)

Download
--------

Gradle:

```gradle
dependencies {
    implementation 'io.inchtime:recyclerkit:0.7.1'
}
```

Maven:

```xml
<dependency>
  <groupId>io.inchtime</groupId>
  <artifactId>recyclerkit</artifactId>
  <version>0.7.1</version>
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

the RecyclerView will show the empty view.
then we can add items to the `adapter`:

```kotlin

val models = arrayListOf(
    RecyclerAdapter.ViewModel(
        R.layout.view_examples_icon,
        1,
        RecyclerAdapter.ModelType.MIDDLE,
        Pair(R.drawable.icon_list, getString(R.string.list))
    ),
    RecyclerAdapter.ViewModel(
        R.layout.view_examples_icon,
        1,
        RecyclerAdapter.ModelType.MIDDLE,
        Pair(R.drawable.icon_appstore, getString(R.string.app_store))
    )
)

adapter.setModels(models)

```

At last, bind the view model:

```kotlin
adapter.onModelViewBind = { _, viewModel, viewHolder ->
    when (viewModel.layout) {
        R.layout.view_examples_icon -> {
            val pair = viewModel.value as Pair<*,*>
            val icon = pair.first as Int
            val title = pair.second as String
            val iconImageView = viewHolder.findView<ImageView>(R.id.iconImageView)
            val titleTextView = viewHolder.findView<TextView>(R.id.titleTextView)
            iconImageView.setImageResource(icon)
            titleTextView.text = title
        }
    }
}
```

Examples
------

[App Store Example By Java][AppStoreExampleJava]

[App Store Example By Kotlin][AppStoreExampleKotlin]

Features
------
- Item operations (set, add, get, remove, replace, clear)
- Item selection (single & multi)
- Item click and long click
- Swipeable items
- Any layout manager
- Default empty view

TODO
------
- [ ] Empty view with multi types e.g. (empty, loading, network error)

Author
------

Evan Cai

QQ: 214390117

Wechat: evancai0523

email: evan-cai@live.cn

License
-------

[MIT][MIT] with [996ICU][996icu].

[AppStoreExampleJava]: https://github.com/inchtime/recyclerkit-android/blob/master/app/src/main/java/io/inchtime/recyclerkit/example/activity/AppStoreJavaExampleActivity.java
[AppStoreExampleKotlin]: https://github.com/inchtime/recyclerkit-android/blob/master/app/src/main/java/io/inchtime/recyclerkit/example/activity/AppStoreExampleActivity.kt
[MIT]: https://opensource.org/licenses/MIT
[996icu]: https://github.com/996icu/996.ICU/blob/master/licenses%5BWIP%5D/LICENSE-MIT-996
