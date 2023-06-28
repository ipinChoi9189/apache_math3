package com.ipinlabs.apache_math3;

import androidx.annotation.NonNull;

import java.util.List;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;

import org.apache.commons.math3.analysis.interpolation.LinearInterpolator;
import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;

import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/** ApacheMath3Plugin */
public class ApacheMath3Plugin implements FlutterPlugin, MethodCallHandler {
  /// The MethodChannel that will the communication between Flutter and native Android
  ///
  /// This local reference serves to register the plugin with the Flutter Engine and unregister it
  /// when the Flutter Engine is detached from the Activity
  private MethodChannel channel;

  private static final String TAG = "ApacheMath3Channel";

  @Override
  public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
    channel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "apache_math3");
    channel.setMethodCallHandler(this);
  }

  @Override
  public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
    if (call.method.equals("getPlatformVersion")) {
      result.success("Android " + android.os.Build.VERSION.RELEASE);
    }else if(call.method.equals("linearErp")){
      List<Integer> t_in = call.argument("input");
      List<Double> originValue = call.argument("value");
      List<Integer> originOutput  = call.argument("output");

      assert t_in != null;
      assert  originValue != null;
      assert  originOutput != null;

      List<Long> longList = new ArrayList<>();
      for (Integer value : t_in) {
        longList.add(value.longValue());
      }
      List<Long> longTin = longList;

      List<Float> floatValue = new ArrayList<>();
      for (Double element : originValue) {
        floatValue.add(element.floatValue());
      }

      long[] t_out = new long[originOutput.size()];
      for (int i = 0; i < originOutput.size(); i++) {
        t_out[i] = originOutput.get(i).longValue();
      }

      LinearInterpolator li = new LinearInterpolator();
      PolynomialSplineFunction psf =
              li.interpolate(longTin.stream().mapToDouble(Long::doubleValue).toArray(),
                      floatValue.stream().mapToDouble(Float::doubleValue).toArray());
      result.success(Arrays.stream(t_out).mapToDouble(psf::value).toArray());

    }else {
      result.notImplemented();
    }
  }

  @Override
  public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
    channel.setMethodCallHandler(null);
  }
}
