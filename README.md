## 文字列を１行毎に分割

### 概要
ゲームの会話メッセージ表示などの、文字列を１行毎に処理。

## 課題 1. 改行コードで分割、リストに変換
次のような文字列分割を行う StringsSplitter クラスを作成しなさい

```
●複数行の文字列を、改行コードで分割して、１行毎の文字列のリストに変換する
●改行コードが複数連続している場合は、空行もリストに追加する
●メソッド名は splitWithLineBreakCode とする
```

### Javaコード
``` java
List<String> lines = splitWithLineBreakCode(
    "１行目。\n２行目。\n３行目。\n４行目。\n\n５行目\n"
);

for (String line : lines) {
    System.out.println(line);
}
```

### 結果
``` console
１行目。
２行目。
３行目。
４行目。

５行目

```

## 課題 2. さらに句点でも分割
課題 1 の処理に次の仕様を加えた StringsMoreSplitter クラスを作成しなさい

```
●句点（。）でも分割する
●ただし、句点（。）の直後に改行（\n）がある場合は、句点（。）の直後の改行（\n）は無視する
●メソッド名は splitWithLineBreakCodeAndPeriod とする
```

### Javaコード
``` java
List<String> lines = splitWithLineBreakCodeAndPeriod(
    "１行目。２行目。\n３行目。４行目。\n\n５行目。"
);

for (String line : lines) {
    System.out.println(line);
}
```

### 結果
``` console
１行目。
２行目。
３行目。
４行目。

５行目。

```

## 課題 3. さらに固定長で分割
課題 2 の処理に次の仕様を加えた StringsFixedLengthSplitter クラスを作成しなさい

```
●文字列を指定した長さで分割する
●メソッド名は splitFixedLengthWithLineBreakCodeAndPeriod とする
```

### Javaコード
``` java
List<String> lines = splitFixedLengthWithLineBreakCodeAndPeriod(
    "このプログラムは、文字列を指定された幅で改行するサンプルプログラムです。",
    6
);

for (String line : lines) {
    System.out.println(line);
}
```

### 結果
``` console
このプログラ
ムは、文字列
を指定された
幅で改行する
サンプルプロ
グラムです。

```

## 課題 4. さらに禁則処理を追加
課題 3 の処理に次の禁則処理を追加した StringsJaHyphenationSplitter クラスを作成しなさい

```
●行頭禁則文字を句読点（、。）とする
●メソッド名は splitFixedLengthJaHyphenationWithLineBreakCodeAndPeriod とする
```

### Javaコード
``` java
List<String> lines =
    splitFixedLengthJaHyphenationWithLineBreakCodeAndPeriod(
        "このプログラムは、句読点を行頭禁則処理するサンプル。\n"
        + "最後の行です",
        8
    );

for (String line : lines) {
    System.out.println(line);
}
```

### 結果
``` console
このプログラムは、
句読点を行頭禁則
処理するサンプル。
最後の行です
```