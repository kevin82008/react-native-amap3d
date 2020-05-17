import React, { Component } from 'react'
import { StyleSheet, Alert } from 'react-native'
import { MapView } from 'react-native-amap3d'

export default class ClusterPointExample extends Component {
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
    Alert.alert(JSON.stringify(data))
  }

  render() {
    return (
      <MapView zoomLevel={12} style={StyleSheet.absoluteFill}>
        <MapView.ClusterPoint
          image="point"
          points={this._points}
          //onClusterPointClick={this._onClusterPointClick}
        />
      </MapView>
    )
  }
}

