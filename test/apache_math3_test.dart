import 'package:flutter_test/flutter_test.dart';
import 'package:apache_math3/apache_math3.dart';
import 'package:plugin_platform_interface/plugin_platform_interface.dart';

class MockApacheMath3
    with MockPlatformInterfaceMixin
    implements ApacheMath3 {
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
  final ApacheMath3 initialPlatform = ApacheMath3();

  test('$ApacheMath3 is the default instance', () {
    expect(initialPlatform, isInstanceOf<ApacheMath3>());
  });
}
