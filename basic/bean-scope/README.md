
| scope | 説明 |
|-------|-----|
|singleton |DIコンテナの起動時にBeanのインスタンスを生成し、同一のインスタンスを共有して利用する。<br/>デフォルトの設定で、scopeを設定しない場合はsingletonとして扱われる|
|prototype |Beanの取得時い毎回インスタンスを生成する。スレッドアンセーフなBeanの場合、singletonを利用できないためprototypeを利用する|
|session |HTTPのセッション単位でBeanのインスタンスを生成する。<br>Webアプリケーションの場合のみ有効|
|request |HTTPのリクエスト単位でBeanのインスタンスを生成する。<br>Webアプリケーションの場合のみ有効|
|application |サーブレットのコンテキスト単位でBeanのインスタンスを生成する。<br>Webアプリケーションの場合のみ有効|
