import Flutter
import UIKit
func noop(){}
public class SwiftFlutterAliyunLogPlugin: NSObject, FlutterPlugin {
  public var  client:LOGClient?;
  public static func register(with registrar: FlutterPluginRegistrar) {
    let channel = FlutterMethodChannel(name: "flutter_aliyun_log", binaryMessenger: registrar.messenger())
    let instance = SwiftFlutterAliyunLogPlugin()
    registrar.addMethodCallDelegate(instance, channel: channel)
  }

  public func handle(_ call: FlutterMethodCall, result: @escaping FlutterResult) {
    if(call.method == "create"){
        let params = call.arguments  as! [String:Any];
        let config = SLSConfig(connectType: .wifiOrwwan, cachable: true)
        self.client =   LOGClient(
            endPoint: params["endPoint"] as! String,
            accessKeyID: params["accessKeyID"]  as! String,
            accessKeySecret: params["accessKeySecret"]  as! String,
            projectName: params["projectName"] as! String,
            token: params["token"] as? String,
            config:config
        );
        
        result("created");
    }
    else if(call.method == "post"){
        let post = call.arguments  as! [String:Any];
        let store       = post["store"] as! String;
        let topic       = post["topic"] as! String;
        let logs        = post["keyContents"]  as! [[String:String]];
        let source      = post["source"]  as! String;
        self.post(store:store,topic: topic,source:source,logs:logs)
        result("posted")
    }

  }
  
  func post(store:String,topic:String,source:String,logs:[[String:String]]){
        guard (self.client != nil) else {
            return ;
        }
        //每次创建都需要新建一个log group
        let group = LogGroup(topic:topic,source:source);
        let logItem = Log();
        for log in logs {
            let key = log["key"] ;
            let value = log["content"] ;
            logItem.PutContent(key!, value: value!);
        }
        group.PutLog(logItem);
        self.client!.PostLog(group, logStoreName:store, call:{(response,error) in noop();})
    }
    
}
