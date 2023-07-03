import 'package:plugin_platform_interface/plugin_platform_interface.dart';

import 'apache_math3_method_channel.dart';

abstract class ApacheMath3Platform extends PlatformInterface {
  /// Constructs a ApacheMath3Platform.
  ApacheMath3Platform() : super(token: _token);

  static final Object _token = Object();

  static ApacheMath3Platform _instance = MethodChannelApacheMath3();

  /// The default instance of [ApacheMath3Platform] to use.
  ///
  /// Defaults to [MethodChannelApacheMath3].
  static ApacheMath3Platform get instance => _instance;

  /// Platform-specific implementations should set this with their own
  /// platform-specific class that extends [ApacheMath3Platform] when
  /// they register themselves.
  static set instance(ApacheMath3Platform instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }

  Future<String?> getPlatformVersion() {
    throw UnimplementedError('platformVersion() has not been implemented.');
  }

  Future<List<double>?> linearErp({
    required List<int> input,
    required List<double> value,
    required List<int> output,
  }) {
    throw UnimplementedError('linearErp() has not been implemented.');
  }
}
