package com.yaojie.swiperecyclerviewdemo;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.ViewGroup;
import android.widget.Toast;

import com.yanzhenjie.recyclerview.swipe.Closeable;
import com.yanzhenjie.recyclerview.swipe.OnSwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Activity mContext;
    private SwipeMenuRecyclerView mView;
    private List<bean> beans;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext=this;
        initBeans();
        mView = (SwipeMenuRecyclerView) findViewById(R.id.swipe_recyclerview);
        mView.setLayoutManager(new LinearLayoutManager(this));
        mView.addItemDecoration(new ListViewDecoration(mContext));// 添加分割线。
        // 为SwipeRecyclerView的Item创建菜单就两句话，不错就是这么简单：
        // 设置菜单创建器。
        mView.setSwipeMenuCreator(swipeMenuCreator);
        // 设置菜单Item点击监听。
        mView.setSwipeMenuItemClickListener(menuItemClickListener);
        adapter = new MyAdapter(beans);
        adapter.setOnItemClickListener(onItemClickListener);
        mView.setAdapter(adapter);
    }

    private void initBeans() {
        beans = new ArrayList<bean>();
        bean aa = new bean("头条", R.drawable.aa);
        bean bb = new bean("娱乐", R.drawable.bb);
        bean cc = new bean("科技", R.drawable.cc);
        bean dd = new bean("军事", R.drawable.dd);
        bean ee = new bean("数码", R.drawable.ee);
        bean ff = new bean("社会", R.drawable.ff);
        bean gg = new bean("时尚", R.drawable.gg);
        bean hh = new bean("艺术", R.drawable.hh);
        bean ii = new bean("游戏", R.drawable.ii);
        bean jj = new bean("财经", R.drawable.jj);
        beans.add(aa);
        beans.add(bb);
        beans.add(cc);
        beans.add(dd);
        beans.add(ee);
        beans.add(ff);
        beans.add(gg);
        beans.add(hh);
        beans.add(ii);
        beans.add(jj);
    }

    /**
     * 菜单创建器
     */
    private SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator(){

        @Override
        public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {
            int width = getResources().getDimensionPixelSize(R.dimen.item_height);
            // MATCH_PARENT 自适应高度，保持和内容一样高；也可以指定菜单具体高度，也可以用WRAP_CONTENT。
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            SwipeMenuItem deleteItem = new SwipeMenuItem(mContext)
                    .setBackgroundDrawable(R.drawable.selector_red)
                    .setImage(R.mipmap.ic_action_delete)
                    .setText("删除") // 文字，还可以设置文字颜色，大小等。。
                    .setTextColor(Color.WHITE)
                    .setWidth(width)
                    .setHeight(height);
            swipeRightMenu.addMenuItem(deleteItem);
            SwipeMenuItem addItem = new SwipeMenuItem(mContext)
                    .setBackgroundDrawable(R.drawable.selector_pinlpe)
                    .setImage(R.mipmap.ic_action_add)
                    .setText("增加")
                    .setTextColor(Color.WHITE)
                    .setWidth(width)
                    .setHeight(height);
            swipeRightMenu.addMenuItem(addItem);
            SwipeMenuItem cancelItem = new SwipeMenuItem(mContext)
                    .setBackgroundDrawable(R.drawable.selector_green)
                    .setText("取消")
                    .setTextColor(Color.WHITE)
                    .setWidth(width)
                    .setHeight(height);
            swipeRightMenu.addMenuItem(cancelItem); // 添加一个按钮到右侧菜单。
        }
    };
    /**
     * 菜单点击监听。
     */
    private OnSwipeMenuItemClickListener menuItemClickListener = new OnSwipeMenuItemClickListener() {
        /**
         * Item的菜单被点击的时候调用。
         * @param closeable       closeable. 用来关闭菜单。
         * @param adapterPosition adapterPosition. 这个菜单所在的item在Adapter中position。
         * @param menuPosition    menuPosition. 这个菜单的position。比如你为某个Item创建了2个MenuItem，那么这个position可能是是 0、1，
         * @param direction       如果是左侧菜单，值是：SwipeMenuRecyclerView#LEFT_DIRECTION，如果是右侧菜单，值是：SwipeMenuRecyclerView
         *                        #RIGHT_DIRECTION.
         */
        @Override
        public void onItemClick(Closeable closeable, int adapterPosition, int menuPosition, int direction) {
            closeable.smoothCloseMenu();// 关闭被点击的菜单。

            if (direction == SwipeMenuRecyclerView.RIGHT_DIRECTION) {
                Toast.makeText(mContext, "list第" + adapterPosition + "; 右侧菜单第" + menuPosition, Toast.LENGTH_SHORT).show();
            }
            // TODO 如果是删除：推荐调用Adapter.notifyItemRemoved(position)，不推荐Adapter.notifyDataSetChanged();
            if (menuPosition == 0) {// 删除按钮被点击。
                beans.remove(adapterPosition);
                adapter.notifyItemRemoved(adapterPosition);
            }
        }
    };
    /**
     * Item点击监听。
     */
    private OnItemClickListener onItemClickListener = new OnItemClickListener() {
        @Override
        public void onItemClick(int position) {
            Toast.makeText(mContext, "我是第" + position + "条!", Toast.LENGTH_SHORT).show();
        }
    };
}
