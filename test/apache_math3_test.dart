import 'package:flutter_test/flutter_test.dart';
import 'package:apache_math3/apache_math3.dart';
import 'package:apache_math3/apache_math3_platform_interface.dart';
import 'package:apache_math3/apache_math3_method_channel.dart';
import 'package:plugin_platform_interface/plugin_platform_interface.dart';

class MockApacheMath3Platform
    with MockPlatformInterfaceMixin
    implements ApacheMath3Platform {
  @override
  Future<String?> getPlatformVersion() => Future.value('42');

  @override
  Future<List<double>> linearErp(
      {required List<int> input,
      required List<double> value,
      required List<int> output}) {
    throw UnimplementedError();
  }
}

void main() {
  final ApacheMath3Platform initialPlatform = ApacheMath3Platform.instance;

  test('$MethodChannelApacheMath3 is the default instance', () {
    expect(initialPlatform, isInstanceOf<MethodChannelApacheMath3>());
  });

  test('getPlatformVersion', () async {
    MockApacheMath3Platform fakePlatform = MockApacheMath3Platform();
    ApacheMath3Platform.instance = fakePlatform;

    expect(await ApacheMath3.getPlatformVersion()?? '42', '42');
  });
}
