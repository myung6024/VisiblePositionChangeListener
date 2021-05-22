import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class VisiblePositionChangeListener(
    linearLayoutManager: LinearLayoutManager,
    private val listener: OnChangeListener
) : RecyclerView.OnScrollListener() {
    interface OnChangeListener {
        fun onFirstVisiblePositionChanged(position: Int)
        fun onLastVisiblePositionChanged(position: Int)
        fun onFirstInvisiblePositionChanged(position: Int)
        fun onLastInvisiblePositionChanged(position: Int)
    }

    private var firstVisiblePosition: Int = RecyclerView.NO_POSITION
    private var lastVisiblePosition: Int = RecyclerView.NO_POSITION
    private var layoutManager: LinearLayoutManager = linearLayoutManager


    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val firstPosition = layoutManager.findFirstVisibleItemPosition()
        val lastPosition = layoutManager.findLastVisibleItemPosition()
        if (firstVisiblePosition == RecyclerView.NO_POSITION || lastVisiblePosition == RecyclerView.NO_POSITION) {
            firstVisiblePosition = firstPosition
            lastVisiblePosition = lastPosition
            return
        }
        if (firstPosition < firstVisiblePosition) {
            if (firstVisiblePosition - firstPosition > 1) {
                for (i in 1 until firstVisiblePosition - firstPosition + 1) {
                    listener.onFirstVisiblePositionChanged(firstVisiblePosition - i)
                }
            } else {
                listener.onFirstVisiblePositionChanged(firstPosition)
            }
            firstVisiblePosition = firstPosition
        } else if (firstPosition > firstVisiblePosition) {
            if (firstPosition - firstVisiblePosition > 1) {
                for (i in firstPosition - firstVisiblePosition downTo 1) {
                    listener.onFirstInvisiblePositionChanged(firstPosition - i)
                }
            } else {
                listener.onFirstInvisiblePositionChanged(firstVisiblePosition)
            }
            firstVisiblePosition = firstPosition
        }
        if (lastPosition > lastVisiblePosition) {
            if (lastPosition - lastVisiblePosition > 1) {
                for (i in 1 until lastPosition - lastVisiblePosition + 1) {
                    listener.onLastVisiblePositionChanged(lastVisiblePosition + i)
                }
            } else {
                listener.onLastVisiblePositionChanged(lastPosition)
            }
            lastVisiblePosition = lastPosition
        } else if (lastPosition < lastVisiblePosition) {
            if (lastVisiblePosition - lastPosition > 1) {
                for (i in 0 until lastVisiblePosition - lastPosition) {
                    listener.onLastInvisiblePositionChanged(lastVisiblePosition - i)
                }
            } else {
                listener.onLastInvisiblePositionChanged(lastVisiblePosition)
            }
            lastVisiblePosition = lastPosition
        }
    }
}
