import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

import 'apache_math3_platform_interface.dart';

/// An implementation of [ApacheMath3Platform] that uses method channels.
class MethodChannelApacheMath3 extends ApacheMath3Platform {
  /// The method channel used to interact with the native platform.
  @visibleForTesting
  final methodChannel = const MethodChannel('apache_math3');

  @override
  Future<String?> getPlatformVersion() async {
    final version = await methodChannel.invokeMethod<String>('getPlatformVersion');
    return version;
  }

  @override
  Future<List<double>?> linearErp({
    required List<int> input,
    required List<double> value,
    required List<int> output,
  }) async {
    try{
      print('dart channel start');
      final result = await methodChannel.invokeMethod<List<dynamic>?>('linearErp',
          {"input": input, "value": value, "output": output}) ??
          [0.0];

      print('11223333 ${result.length}');
      List<double> doubleList = result.map<double>((dynamic value) => value as double).toList();
      print('vvvfffff ${doubleList.length}');

      print('dart channel finish');
      print(result.toString());

      return doubleList;
    } on PlatformException catch (e) {
      print('?????????');
      return <double>[];
    }catch(e){
      print('method channel error $e');
      throw(e);
    }

  }
}
