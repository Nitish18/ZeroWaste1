package com.example.zerowaste1;

import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.app.ActionBar;
import android.app.SearchManager;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import android.widget.Toast;

@SuppressWarnings("deprecation")
public class MainActivity extends TabActivity  {
	
	String classes[]={"Mercury","Venus"};
	

	TabHost tabHost;
	private DrawerLayout mDrawerLayout;
    
    private ActionBarDrawerToggle mDrawerToggle;

    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    
    
    // List items
    private ListView mDrawerList;
    private String[] drawer_items;
    int[] images = {R.drawable.profile,R.drawable.preference,R.drawable.call,R.drawable.deals,R.drawable.carbon};
    
    
	
	
	// TabSpec Names
    private static final String HOME_SPEC = "HOME";
    private static final String FIND_SPEC = "FIND";
    private static final String CAL_SPEC = "CAL";
    private static final String ABOUT_SPEC = "ABOUT";
	
	
    
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		
		
		//Tab Started
		
		tabHost = (TabHost)findViewById(android.R.id.tabhost);
	    

		 TabHost tabHost = getTabHost();
         
				 
	        // Home Tab
	        TabSpec homeSpec = tabHost.newTabSpec(HOME_SPEC);
	        
	        // Tab Icon
	        homeSpec.setIndicator(HOME_SPEC, getResources().getDrawable(R.drawable.icon_home));
	        Intent homeIntent = new Intent(this,Home.class);
	        // Tab Content
	        homeSpec.setContent(homeIntent);

		
		
	     // Find Tab
	        TabSpec findSpec = tabHost.newTabSpec(FIND_SPEC);
	        findSpec.setIndicator(FIND_SPEC, getResources().getDrawable(R.drawable.find_icon));
	        Intent findIntent = new Intent(this, Find.class);
	        findSpec.setContent(findIntent);
	         
	        
	        // cal Tab
	        TabSpec calSpec = tabHost.newTabSpec(CAL_SPEC);
	        calSpec.setIndicator(CAL_SPEC, getResources().getDrawable(R.drawable.calculate_icon));
	        Intent calIntent = new Intent(this,Calculate.class);
	        calSpec.setContent(calIntent);
		
		
	        // about Tab
	        TabSpec aboutSpec = tabHost.newTabSpec(ABOUT_SPEC);
	        aboutSpec.setIndicator(ABOUT_SPEC, getResources().getDrawable(R.drawable.about_icon));
	        Intent aboutIntent = new Intent(this,About.class);
	        aboutSpec.setContent(aboutIntent);
		
		
	        tabHost.addTab(homeSpec); 
	        tabHost.addTab(findSpec); 
	        tabHost.addTab(calSpec);
	        tabHost.addTab(aboutSpec);
		
		
	        //Tab Finished.................................................
	        
	     
	        
	        
	        
	        
	        
	        Resources res = getResources();
	        drawer_items = res.getStringArray(R.array.drawer_list);
	        
	        
	        mTitle = mDrawerTitle = getTitle();
	        
	        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
	       
	        
	        mDrawerList = (ListView) findViewById(R.id.left_drawer);
	        
	        
	        // set a custom shadow that overlays the main content when the drawer opens
	        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
	        
	        
	        
	        //....................................................................................
	    
	        
	        
	        
	        // set up the drawer's list view with items and click listener
	        
	        //mDrawerList.setAdapter(new ArrayAdapter<String>(this,R.layout.custom_drawerlist,drawer_items));
	        
	        
	        
	        
	        VivzAdapter adapter = new VivzAdapter(this,drawer_items,images);
	        
	        mDrawerList.setAdapter(adapter);
	    
	        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
	        
	        
	      
	        //......................................................................................
	        
	        
	        
	        
	        
	        
	        // enable ActionBar app icon to behave as action to toggle nav drawer
	        getActionBar().setDisplayHomeAsUpEnabled(true);
	        getActionBar().setHomeButtonEnabled(true);
	        
	        
	        
	     // ActionBarDrawerToggle ties together the the proper interactions
	        // between the sliding drawer and the action bar app icon
	        mDrawerToggle = new ActionBarDrawerToggle(
	                this,                  /* host Activity */
	                mDrawerLayout,         /* DrawerLayout object */
	                R.drawable.ic_drawer,  /* nav drawer image to replace 'Up' caret */
	                R.string.drawer_open,  /* "open drawer" description for accessibility */
	                R.string.drawer_close  /* "close drawer" description for accessibility */
	                ) {
	            public void onDrawerClosed(View view) {
	                getActionBar().setTitle(mTitle);
	                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
	            }

	            public void onDrawerOpened(View drawerView) {
	                getActionBar().setTitle(mDrawerTitle);
	                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
	            }
	        };
	        mDrawerLayout.setDrawerListener(mDrawerToggle);
	        
	        
	        if (savedInstanceState == null) {
	            selectItem(0);
	        }
	    }
	
	
	 @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        MenuInflater inflater = getMenuInflater();
	        inflater.inflate(R.menu.main, menu);
	        return super.onCreateOptionsMenu(menu);
	    }
	 
	 
	 /* Called whenever we call invalidateOptionsMenu() */
	    @Override
	    public boolean onPrepareOptionsMenu(Menu menu) {
	        // If the nav drawer is open, hide action items related to the content view
	        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
	        menu.findItem(R.id.action_websearch).setVisible(!drawerOpen);
	        return super.onPrepareOptionsMenu(menu);
	    }
	        
	   
	    @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	         // The action bar home/up action should open or close the drawer.
	         // ActionBarDrawerToggle will take care of this.
	        if (mDrawerToggle.onOptionsItemSelected(item)) {
	            return true;
	        }
	        // Handle action buttons
	        switch(item.getItemId()) {
	        case R.id.action_websearch:
	            // create intent to perform web search for this planet
	            Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
	            intent.putExtra(SearchManager.QUERY, getActionBar().getTitle());
	            // catch event that there's no activity to handle intent
	            if (intent.resolveActivity(getPackageManager()) != null) {
	                startActivity(intent);
	            } else {
	                Toast.makeText(this, R.string.app_not_available, Toast.LENGTH_LONG).show();
	            }
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	        }
	    }
	    
	    
	    /* The click listner for ListView in the navigation drawer */
	    private class DrawerItemClickListener implements ListView.OnItemClickListener {
	        @Override
	        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
	            selectItem(position);
	        }
	    }
	    
	    
	    private void selectItem(int position){
	    	
	    	String cheese = classes[position];
			try{
			Class ourClasses = Class.forName("com.example.zerowaste1."+cheese);
			Intent ourIntent = new Intent(MainActivity.this,ourClasses);
			startActivity(ourIntent);
			}
			
			
			catch(ClassNotFoundException e)
			{
				e.printStackTrace();
			}
	    	
	        
	      	
	}

	
	    @Override
	    public void setTitle(CharSequence title) {
	        mTitle = title;
	        getActionBar().setTitle(mTitle);
	    }
	    
	    
	    @Override
	    protected void onPostCreate(Bundle savedInstanceState) {
	        super.onPostCreate(savedInstanceState);
	        // Sync the toggle state after onRestoreInstanceState has occurred.
	        mDrawerToggle.syncState();
	    }
	    
	    
	    @Override
	    public void onConfigurationChanged(Configuration newConfig) {
	        super.onConfigurationChanged(newConfig);
	        // Pass any configuration change to the drawer toggls
	        mDrawerToggle.onConfigurationChanged(newConfig);
	    }
	    
	    
	    
	   
	}



// Vivz Adpater Class....................................................................




  class VivzAdapter extends ArrayAdapter<String> {
	
	
	  
	 
	  
	  
	  Context context;
	int[] images1;
	String[] ditems;
	
	
	
	
	
	
	VivzAdapter(Context c,String[] items,int imgs[]){
		
		super(c,R.layout.custom_drawerlist,R.id.drawerTV,items);
		this.context=c;
		this.images1=imgs;
		this.ditems=items;
		
		
	}

  
	
	

	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View vi = inflater.inflate(R.layout.custom_drawerlist,parent,false);
		
		ImageView imageview = (ImageView) vi.findViewById(R.id.drawerIV);
		TextView textview = (TextView) vi.findViewById(R.id.drawerTV);
		
		
		imageview.setImageResource(images1[position]);
		
		textview.setText(ditems[position]);
		
		return vi;
	
	
	}
	

}

  
  





