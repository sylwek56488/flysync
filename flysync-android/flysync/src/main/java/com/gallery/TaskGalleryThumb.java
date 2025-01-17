package com.gallery;

import java.io.IOException;
import java.util.Map;

import com.pathik.server.HTTPBaseTask;
import com.pathik.server.HTTPResponeUtil;
import com.pathik.service.MyService;
import com.util.TaskUtil;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import fi.iki.elonen.NanoHTTPD;
import fi.iki.elonen.NanoHTTPD.Response;

public class TaskGalleryThumb implements HTTPBaseTask{

	
	private Context context = MyService.context;
	private ContentResolver resolver = context.getContentResolver();
	
	@Override
	public Response work(Map<String, String> args) {
		Long _ID = Long.parseLong(args.get("id"));
		Bitmap bitmap = MediaStore.Images.Thumbnails.getThumbnail(resolver, _ID, MediaStore.Images.Thumbnails.MICRO_KIND	, null);
		try {
			NanoHTTPD.Response response = HTTPResponeUtil.newFixedFileResponse(TaskUtil.bitmapToInputStream(bitmap), "image/png");
			return response;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
