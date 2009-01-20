package controllers

import org.jdesktop.animation.timing.Animator
import org.jdesktop.animation.timing.interpolation.KeyValues
import org.jdesktop.animation.timing.interpolation.KeyTimes
import org.jdesktop.animation.timing.interpolation.Interpolator
import java.awt.Point
import org.jdesktop.animation.timing.interpolation.KeyFrames
import org.jdesktop.animation.timing.interpolation.PropertySetter
import helpers.Positionning


class AnimationController {


    /**
    * Current animation
    *
    */
    def animation = null


    /**
    * Slide the given ScrollPane to the right index
    *
    * @param    component   Component included
    * @param    scrollpane  Scrollpane where the component is included
    * @param    index       Where you need to slide to
    * @return   gap needed for a container
    */
    def slideViewPositionTo(component, scrollpane, index)
    {

        if (animation != null && animation.isRunning()) {
            animation.stop();
        }

        int initial = Positionning.initialPosition(component, scrollpane)
        int viewportw = scrollpane.getViewport().getSize().width
        Point current = scrollpane.getViewport().getViewPosition()
        Point to = new Point((int) (initial + index * (viewportw + component.getSize().width)), (int) current.y)
        int diff = to.x - current.x


        Animator.Direction direction = Animator.Direction.FORWARD
        Animator.RepeatBehavior repeatBehavior = Animator.RepeatBehavior.REVERSE
        Animator.EndBehavior behavior = Animator.EndBehavior.HOLD

        int numKeyframes = 2


        KeyTimes keyTimes = new KeyTimes(0.0f, 1.0f);
        KeyValues keyValues = KeyValues.create(
             current,
             to
        );

        def keyFrames = new KeyFrames(keyValues, keyTimes, (Interpolator)null);

        animation = new Animator(2000, 1, repeatBehavior,
                new PropertySetter(scrollpane.getViewport(), "ViewPosition", keyFrames));
        animation.setResolution(0)
        animation.setStartDelay(0)
        animation.setEndBehavior(behavior)
        animation.setStartFraction(0.0f)
        animation.setStartDirection(direction)

        animation.setAcceleration(0.2f)
        animation.setDeceleration(0.8f)

        animation.start();

    }

    





}