package com.example.consumingrestapisampleapp;

import java.io.InputStream;
import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class JsonParsingAdapter extends ArrayAdapter<JsonPojoClass> {

	ArrayList<JsonPojoClass> actorList;
	LayoutInflater vi;
	int Resource;
	ViewHolder holder;

	public JsonParsingAdapter(Context context, int resource,
			ArrayList<JsonPojoClass> objects) {
		super(context, resource, objects);
		vi = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		Resource = resource;
		actorList = objects;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		if (view == null) {
			holder = new ViewHolder();
			view = vi.inflate(Resource, null);
			holder.imageview = (ImageView) view.findViewById(R.id.ivImage);
			holder.tvTitle = (TextView) view.findViewById(R.id.title);
			holder.tvDescription = (TextView) view
					.findViewById(R.id.description);

			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}
		holder.imageview.setImageResource(R.drawable.ic_launcher);

		new DownloadImageTask(holder.imageview).execute(actorList.get(position)
				.getmImage());
		holder.tvTitle.setText(actorList.get(position).getmTile());
		holder.tvDescription
				.setText(actorList.get(position).getmDescripition());

		return view;

	}

	static class ViewHolder {
		public ImageView imageview;
		public TextView tvTitle;
		public TextView tvDescription;

	}

	private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
		ImageView bmImage;

		public DownloadImageTask(ImageView bmImage) {
			this.bmImage = bmImage;
		}

		protected Bitmap doInBackground(String... urls) {
			String urldisplay = urls[0];
			Bitmap mIcon11 = null;
			try {
				InputStream in = new java.net.URL(urldisplay).openStream();
				mIcon11 = BitmapFactory.decodeStream(in);
			} catch (Exception e) {

				e.printStackTrace();
			}
			return mIcon11;
		}

		protected void onPostExecute(Bitmap result) {
			bmImage.setImageBitmap(result);
		}

	}
}
