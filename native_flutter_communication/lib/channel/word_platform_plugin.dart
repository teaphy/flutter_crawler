import 'package:english_words/english_words.dart';
import 'package:flutter/services.dart';

const BasicMessageChannel wordMessageChannel = BasicMessageChannel(
    "com.example.native_flutter_communication/word", StringCodec());

const BasicMessageChannel randomMessageChannel = BasicMessageChannel(
    "com.example.native_flutter_communication/random", StandardMessageCodec());

void sendWord() {
  print("send word");

  Stream.periodic(Duration(seconds: 1), (count) => count).listen((count) {
    print("send word - count: $count");
    String word = all[count];
    print("send word: $word");
    _sendWordExe(word);
  });
}

Future _sendWordExe(String word) async {
  var reply = await wordMessageChannel.send(word);
  print("_sendWordExe reply：$reply");
}
