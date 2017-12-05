# filter

|共通処理の種類| 説明 |
|-------|-----|
|ServletRequestListener |リクエスト開始時とリクエスト終了時のタイミングで任意の処理を実行できる|
|Filter |Servletの呼び出し前後に任意の処理を実行できる|
|HandlerInterceptor |Handlerメソッドの呼び出し前後に任意の処理を実行できる|
|@ControllerAdvice |@ExceptionHandlerメソッドなどを複数のControllerで共通する場合などに使用する|


##### リクエスト開始時に、この順番で呼ばれる
1. ServletRequestListener::requestInitialized
2. RequestFilter::doFilterInternal
3. HandlerInterceptor::preHandle

##### リクエスト終了時に、この順番で呼ばれる
1. HandlerInterceptor::postHandle
2. HandlerInterceptor::afterCompletion
3. ServletRequestListener::requestDestroyed
