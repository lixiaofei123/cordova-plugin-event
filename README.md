<!---
 license: Licensed to the Apache Software Foundation (ASF) under one
         or more contributor license agreements.  See the NOTICE file
         distributed with this work for additional information
         regarding copyright ownership.  The ASF licenses this file
         to you under the Apache License, Version 2.0 (the
         "License"); you may not use this file except in compliance
         with the License.  You may obtain a copy of the License at

           http://www.apache.org/licenses/LICENSE-2.0

         Unless required by applicable law or agreed to in writing,
         software distributed under the License is distributed on an
         "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
         KIND, either express or implied.  See the License for the
         specific language governing permissions and limitations
         under the License.
-->

# org.apache.cordova.device

Plugin documentation: [doc/index.md](doc/index.md)

java代码调用

```java

// 可以多次调用
EventPlugin.onEvent(new EventPlugin.EventListener() {
	@Override
	public String type() {
		return "test";
	}

	@Override
	public boolean handleEvent(final EventPlugin.Event event) {
		Log.i("test","EventPlugin::test::" + event.data);
		
		// 如果修改页面UI元素，需要在runOnUiThread()中进行修改
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				showEvent.setText(event.data);
			}
		});
		
		// 如果返回true,则该事件不会继续向下传播
		return false;
	}
});
```


js端调用
```javascript
	// 第一个参数需要和java代码中的type()相匹配才会被java代码处理
	cordova.plugins.eventPlugin.event("test","good monring")
```

