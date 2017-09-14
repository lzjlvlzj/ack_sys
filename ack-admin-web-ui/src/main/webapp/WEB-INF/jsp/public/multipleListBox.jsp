<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="bootstrap-duallistbox-container row moveonselect">
	<div class="box1 col-md-6">
	    <span class="info-container">     
	        <span class="info">待参与全部人员</span>        
	    </span>
		<div class="btn-group buttons">
			<button type="button" class="btn moveall btn-white btn-bold btn-info"
				title="Move all" id="multiple-box-list-src-btn">
				<i class="fa fa-arrow-right"></i> <i class="fa fa-arrow-right"></i>
			</button>
		</div>
		<select multiple="multiple"
			id="multiple-box-list-src"
			class="form-control" 
			name="multiple-box-list-src"
			style="height: 210px"
			size="10">
			<!--  
			<option value="option1">Option 1</option>
			<option value="option2">Option 2</option>
			<option value="option4">Option 4</option>
			<option value="option5">Option 5</option>
			<option value="option7">Option 7</option>
			<option value="option8">Option 8</option>
			<option value="option9">Option 9</option>
			<option value="option0">Option 10</option>
			-->
		</select>
	</div>
	<div class="box2 col-md-6">
	    <span class="info-container">     
	        <span class="info">已经参与人员</span>        
	    </span>
		<div class="btn-group buttons">
			<button type="button" id="multiple-box-list-target-btn"
				class="btn removeall btn-white btn-bold btn-info" title="Remove all">
				<i class="fa fa-arrow-left"></i> <i class="fa fa-arrow-left"></i>
			</button>
		</div>
		<select multiple="multiple"
			id="multiple-box-list-target"
			class="form-control"
			style="height: 210px" 
			name="multiple-box-list-target"
			size="10">
			<!--
			<option value="option3" selected="selected">Option 3</option>
			<option value="option6" selected="selected">Option 6</option>
			-->
		</select>
	</div>
</div>
