import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:native_flutter_communication/commodity_page.dart';
import 'package:native_flutter_communication/random_word_page.dart';
import 'channel/word_platform_plugin.dart';
import 'const/account_const.dart';
import 'model/account_bean.dart';
import 'model/account_status.dart';
import 'package:english_words/english_words.dart';

class HomePage extends StatefulWidget {
  HomePage({Key key}) : super(key: key);

  @override
  _HomePageState createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  static const platformAccount =
      const MethodChannel('com.example.native_flutter_communication/account');
  var resultNative = "";

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Text("HomePage"),
        ),
        body: _generateBody());
  }

  Widget _generateBody() {
    return Center(
        child: Column(
      mainAxisAlignment: MainAxisAlignment.center,
      crossAxisAlignment: CrossAxisAlignment.center,
      children: <Widget>[
        _getAccountWidget(),
        RaisedButton(
          onPressed: _gotoRandomWordNative,
          child: Text("RandomWordNative"),
        ),
        RaisedButton(
          onPressed: _gotoRandomWordFlutter,
          child: Text("RandomWordFlutter"),
        ),
        RaisedButton(
          onPressed: _gotoRCommodityPageFlutter,
          child: Text("CommodityPageFlutter"),
        )
      ],
    ));
  }

  Widget _getAccountWidget() {
    Widget widget;
    if (AccountConst.accountStatus == AccountStatus.offline) {
      widget = FlatButton(
        onPressed: _gotoLogin,
        child: Text("Login"),
      );
    } else {
      widget = Column(
        mainAxisAlignment: MainAxisAlignment.center,
        crossAxisAlignment: CrossAxisAlignment.center,
        children: <Widget>[
          Text("name: ${AccountConst.accountBean.name}"),
          Text("mobile: ${AccountConst.accountBean.mobile}"),
          FlatButton(
            onPressed: _loginOut,
            child: Text("LoginOut"),
          ),
          FlatButton(
            onPressed: _gotoEditAccount,
            child: Text("EditAccount"),
          )
        ],
      );
    }

    return widget;
  }

  Future _gotoLogin() async {
    String args = "";
    if (AccountConst.accountBean != null) {
      args = json.encode(AccountConst.accountBean.toJson());
    }
    try {
      String value =
          await platformAccount.invokeMethod<String>("gotoLogin", args);

      print("value: $value");

      Map<dynamic, dynamic> map = json.decode(value);
      AccountBean accountBean = AccountBean.fromJson(map);

      setState(() {
        AccountConst.accountBean = accountBean;
        AccountConst.accountStatus = AccountStatus.online;
      });
    } catch (e) {}
  }

  void _loginOut() {
    setState(() {
      AccountConst.accountBean = null;
      AccountConst.accountStatus = AccountStatus.offline;
    });
  }

  void _gotoEditAccount() async {
    Future future = platformAccount.invokeMethod("gotoEditAccount");
  }

  void _gotoRandomWordNative() async {
    platformAccount.invokeMethod("gotoRandomWord");
    sendWord();
  }

  void _gotoRandomWordFlutter() {
    Navigator.push(
        context, MaterialPageRoute(builder: (context) => RandomWordPage()));
  }

  void _gotoRCommodityPageFlutter() {
    Navigator.push(
        context, MaterialPageRoute(builder: (context) => CommodityPage()));
  }
}
