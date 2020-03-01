// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'account_bean.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

AccountBean _$AccountBeanFromJson(Map<String, dynamic> json) {
  return AccountBean(
    json['name'] as String,
    json['mobile'] as String,
  );
}

Map<String, dynamic> _$AccountBeanToJson(AccountBean instance) =>
    <String, dynamic>{
      'name': instance.name,
      'mobile': instance.mobile,
    };
