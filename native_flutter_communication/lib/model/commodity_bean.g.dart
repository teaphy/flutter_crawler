// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'commodity_bean.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

CommodityBean _$CommodityBeanFromJson(Map<String, dynamic> json) {
  return CommodityBean(
    json['name'] as String,
    json['price'] as String,
  );
}

Map<String, dynamic> _$CommodityBeanToJson(CommodityBean instance) =>
    <String, dynamic>{
      'name': instance.name,
      'price': instance.price,
    };
