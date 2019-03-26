#import "FlutterAliyunLogPlugin.h"
#import <flutter_aliyun_log/flutter_aliyun_log-Swift.h>

@implementation FlutterAliyunLogPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftFlutterAliyunLogPlugin registerWithRegistrar:registrar];
}
@end
