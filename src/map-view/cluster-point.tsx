import * as React from "react";
import * as PropTypes from "prop-types";
import { requireNativeComponent, ViewPropTypes, StyleSheet ,Text} from "react-native";
import { Point } from "../types";
import Component from "./component";
import InfoWindow from "./marker";
import { mapEventsPropType } from "../prop-types";

const style = StyleSheet.create({
  overlay: {
    position: "absolute"
  }
});



const PointPropType = PropTypes.shape({
  latitude: PropTypes.number.isRequired,
  longitude: PropTypes.number.isRequired,
  title: PropTypes.string
});


export interface ClusterPointProps {
  /**
   * 节点
   */
  points?: Point[];

  /**
   * 图标，只接受原生图片名字
   */
  image?: string;

  /**
   * 点击事件
   */
  onClusterPointClick?: (item: Point) => void;

  /**
   * 自定义 InfoWindow
   */
  children?: React.ReactChild;

  clusterKey: string
}

const events = ["onInfoWindowPress"];


/**
 * @ignore
 */
export default class ClusterPoint extends Component<ClusterPointProps> {
  static propTypes = {
    ...ViewPropTypes,
    ...mapEventsPropType(events),
    points: PropTypes.arrayOf(PointPropType).isRequired,
    image: PropTypes.string,
    onClusterPointClick: PropTypes.func
  };

  onClusterPointClick = ({ nativeEvent }) => {
    if (this.props.onClusterPointClick) {
      this.props.onClusterPointClick(nativeEvent);
    }
  };

  /* eslint-disable class-methods-use-this */
  renderInfoWindow(view: React.ReactChild) {
    debugger;
    if (view) {
      return <InfoWindow style={style.overlay}>{view}</InfoWindow>;
    }else{
      return <Text>哈哈</Text>
    }
    return null;
  }

  nativeComponent = "AMapClusterPoint";

  render() {
    debugger;
    return <AMapClusterPoint {...this.props} onClusterPointClick={this.onClusterPointClick} >
      {this.renderInfoWindow(this.props.children)}
      </AMapClusterPoint>;
  }
}

// @ts-ignore
const AMapClusterPoint = requireNativeComponent("AMapClusterPoint", ClusterPoint);
