import 'package:flutter/material.dart';
import 'package:native_flutter_communication/home_page.dart';

import 'channel/edit_platform_plugin.dart';

void main() {
  
  
  runApp(MyApp());
  
  // 初始化Plugin必须放在runApp()之后，否则会报错
  initPlatformPlugin();
}

void initPlatformPlugin() async {
  editMethodChannel.setMethodCallHandler(editChannelHandler);
}

class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'PlatformChannelCrawler',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: HomePage(),
    );
  }
}