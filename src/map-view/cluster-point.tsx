import * as React from "react";
import * as PropTypes from "prop-types";
import { requireNativeComponent, ViewPropTypes } from "react-native";
import { Point } from "../types";

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
}

/**
 * @ignore
 */
export default class ClusterPoint extends React.PureComponent<ClusterPointProps> {
  static propTypes = {
    ...ViewPropTypes,
    points: PropTypes.arrayOf(PointPropType).isRequired,
    image: PropTypes.string,
    onClusterPointClick: PropTypes.func
  };

  onClusterPointClick = ({ nativeEvent }) => {
    if (this.props.onClusterPointClick) {
      this.props.onClusterPointClick(nativeEvent);
    }
  };

  render() {
    return <AMapClusterPoint {...this.props} onClusterPointClick={this.onClusterPointClick} />;
  }
}

// @ts-ignore
const AMapClusterPoint = requireNativeComponent("AMapClusterPoint", ClusterPoint);
