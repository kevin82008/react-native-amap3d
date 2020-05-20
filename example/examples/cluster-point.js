import React, { Component } from 'react'
import { StyleSheet, Alert,Text,TouchableOpacity, View } from 'react-native'
import { MapView } from 'react-native-amap3d'


const styles = StyleSheet.create({
  customIcon: {
    width: 40,
    height: 40
  },
  customInfoWindow: {
    backgroundColor: "#8bc34a",
    padding: 10,
    borderRadius: 10,
    elevation: 4,
    borderWidth: 2,
    borderColor: "#689F38",
    marginBottom: 5
  },
  customMarker: {
    backgroundColor: "#009688",
    alignItems: "center",
    borderRadius: 5,
    padding: 5
  },
  markerText: {
    color: "#fff"
  }
});

export default class ClusterPointExample extends Component {
  state = {
    testValue: '测试值',
    currPoint: {},
    event:{},
    markerId:''
  } 

  showWindow = false;
  static navigationOptions = {
    title: '点聚合',
  }

    _points = Array(1000).fill(0).map((i,idx) => ({
    latitude: 39.5 + Math.random(),
    longitude: 116 + Math.random(),
    id:`12345`+idx,
    title:'测试'+idx
  }))

  //聚合点点击事件，其中data是当前点的数据（只有非聚合时才有值，即 event.nums==1）
  //此时，要设置 currPoint 的值，否则在点击窗体的时候取不到当前节点的值
  _onClusterPointClick = (event,data) => {
    if(event.nums == 1){ //此时会弹出
      this.showWindow = true; //这里只是标记要弹出，在render之后再触发
      this.setState({update:false,event:event,testValue:data.title,currPoint:data})
    }
  }

  //infowindow窗体点击事件，根据 currPoint 取值
  _onInfoWindowPress = (s) => {
    console.log(this.state.currPoint.title);
  }

  //这里才设置 markerId 的值， 是不是更能保证在触发 infoWindow 显示的时候 已经渲染成新的显示内容了？
  
  componentDidUpdate(prevProps) {
    if(this.showWindow){ //判断一定要加，否则死循环
      this.showWindow = false;
      //组件会根据 markerId 的变化来显示 infoWindow，其中，markerId的值 来自 onClusterPointClick 里的 event参数
      this.setState({markerId:this.state.event.markerId})
    }

   
 }

  render() {
    return (
      <MapView zoomLevel={12} style={StyleSheet.absoluteFill}>
        <MapView.ClusterPoint
          image="point"
          clusterKey="test"
          points={this._points}
          markerId={this.state.markerId}
          onClusterPointClick={this._onClusterPointClick}
        >
        <TouchableOpacity activeOpacity={0.9}  onPress={this._onInfoWindowPress}>
            <View style={styles.customInfoWindow}>
             <Text>{this.state.currPoint.title}</Text>
              <Text>{this.state.currPoint.id}</Text>
              <Text>测试下</Text>
            </View>
          </TouchableOpacity>
          </MapView.ClusterPoint>
      </MapView>
    )
  }
}

