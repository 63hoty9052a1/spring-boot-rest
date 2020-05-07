## Spring Boot - Rest API

Springフレームワークを使用したRest APIの実装サンプルです。

## 作成目的
技術検証（Rest API）兼、ポートフォリオ用

## 作成期間
10日

## 所感
サーバーからのレスポンスとして固有のデータ（JSON）を返すため、画面の表示整形に言語やフレームワーク固有のテンプレートエンジンを使用しなければビューとモデルを完全に分離できるのでシステムの換装がしやすいと思った。

## アプリケーションの概要
Youtubeの動画（動画のリンクアドレス）を登録することで、その動画に紐付くデータとしてabemaTVやニコニコ動画のようなコメントを追加および、表示することが出来るWebアプリケーションです。

## 機能概要
- Youtubeの動画（動画のリンクアドレス）登録
- 登録した動画の一覧表示、削除、更新
- 登録した動画の再生とコメント追加

## アプリケーション画面
![アプリ画像](https://user-images.githubusercontent.com/64893747/81176896-cfcd3580-8fe0-11ea-9280-9731c48ac11e.jpg)

## 利用技術（フレームワークおよびライブラリ）
- アプリケーション層
    - フレームワーク：Spring Boot
    - ビルドツール：Maven（Sprign Bootプロジェクト作成時に設定）
    - ORM：Spring Data JPA（内部はHibernateでSprign Bootプロジェクト作成時に設定）
    - HikariCP（Sprign Bootプロジェクト作成時に設定）
    - 主要ライブラリ
        - JSONとJavaBeanの相互変換：Jackson（Sprign Bootプロジェクト作成時に設定）

- データ層
    - データベース：PostgreSQL

- プレゼンテーション層
    - JavaScript
        - Ajax
        - Jquery
        - DataTables

## アプリケーションのUML図
- ER図
### ![er図](https://user-images.githubusercontent.com/64893747/81170565-df934c80-8fd5-11ea-9e19-eb17e9d1ab3d.jpg)

- クラス図
### ![クラス図](https://user-images.githubusercontent.com/64893747/81170954-8c6dc980-8fd6-11ea-9c66-774108400552.jpg)

- シーケンス図
```
一覧表示
```
![一覧検索](https://user-images.githubusercontent.com/64893747/81171927-3f8af280-8fd8-11ea-885c-9718fc874622.jpg)
```
登録/更新/削除
```
![登録更新削除](https://user-images.githubusercontent.com/64893747/81171928-3f8af280-8fd8-11ea-974b-c4e79dec224e.jpg)
```
コメント表示
```
![コメント検索](https://user-images.githubusercontent.com/64893747/81171924-3dc12f00-8fd8-11ea-924a-284e5b6be4a8.jpg)
```
コメント追加
```
![コメント登録](https://user-images.githubusercontent.com/64893747/81171926-3ef25c00-8fd8-11ea-8878-3b68e06fe4d4.jpg)


