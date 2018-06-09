
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.kazuya.jiro.FirstFragment


class MainFragmentPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    //選択したフラグメントにデータを渡して、その閲覧するFragmentを返す
    override fun getItem(position: Int): Fragment? {
        when (position) {
            0 -> {
                return FirstFragment()
            }
            1 -> {
                return FirstFragment()
            }
        }
        return null
    }

    //viewPagerにセットするFragmentの総数を返す
    override fun getCount(): Int {
        return 3
    }

    //各Fragmentのタイトルを返す
    override fun getPageTitle(position: Int): CharSequence? {
        return if (position == 0) {
            "hogehoge"
        } else {
            "piyopiyo"
        }
    }
}