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
    testValue: '测试值'
  }
  static navigationOptions = {
    title: '点聚合',
  }

    _points = Array(1000).fill(0).map((i) => ({
    latitude: 39.5 + Math.random(),
    longitude: 116 + Math.random(),
    id:`12345`,
    title:'测试'
  }))

  _onClusterPointClick = data => {
    this.setState({testValue:"哈哈"})
    Alert.alert(JSON.stringify(data))
  }

  _onInfoWindowPress = (s) => Alert.alert(JSON.stringify(s));

  render() {
    return (
      <MapView zoomLevel={12} style={StyleSheet.absoluteFill}>
        <MapView.ClusterPoint
          image="point"
          clusterKey="test"
          points={this._points}
          //onClusterPointClick={this._onClusterPointClick}
        >
        <TouchableOpacity activeOpacity={0.9}  onPress={this._onInfoWindowPress}>
            <View style={styles.customInfoWindow}>
              <Text>自定义信息窗口</Text>
              <Text>{this.state.testValue}</Text>
            </View>
          </TouchableOpacity>
          </MapView.ClusterPoint>
      </MapView>
    )
  }
}

