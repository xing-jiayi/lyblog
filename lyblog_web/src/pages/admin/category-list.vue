<template>
	<div>
		<!-- 表头分页查询条件， shadow="never" 指定 card 卡片组件没有阴影 -->
		<el-card shadow="never" class="mb-5">
			<!-- flex 布局，内容垂直居中 -->
			<div class="flex items-center">
				<el-text>分类名称</el-text>
				<div class="ml-3 w-52 mr-5"><el-input v-model="searchCategoryName" placeholder="请输入（模糊查询）" /></div>

				<el-text>创建日期</el-text>
				<div class="ml-3 w-30 mr-5">
					<!-- 日期选择组件（区间选择） -->
					<el-date-picker
						v-model="pickDate"
						type="daterange"
						range-separator="至"
						start-placeholder="开始时间"
						end-placeholder="结束时间"
						size="default"
						:shortcuts="shortcuts"
						@change="datepickerChange" />
				</div>

				<el-button type="primary" class="ml-3" :icon="Search" @click="getTableData">查询</el-button>
				<el-button class="ml-3" :icon="RefreshRight" @click="reset">重置</el-button>
			</div>
		</el-card>
		<el-card shadow="never">
			<!-- 新增按钮 -->
			<div class="mb-5">
				<el-button type="primary" @click="addVisible = true">
					<el-icon class="mr-1">
						<Plus />
					</el-icon>
					新增</el-button
				>
			</div>

			<!-- 分页列表 -->
			<el-table :data="tableData" border stripe style="width: 100%" v-loading="tableLoading">
				<el-table-column prop="name" label="分类名称" width="180" />
				<el-table-column prop="createTime" label="创建时间" width="180" />
				<el-table-column label="操作">
					<template #default="scope">
						<el-button type="danger" size="small" @click="handleDeleteCategory(scope.row)">
							<el-icon class="mr-1">
								<Delete />
							</el-icon>
							删除
						</el-button>
					</template>
				</el-table-column>
			</el-table>
			<!-- 分页 -->
			<div class="mt-10 flex justify-center">
				<el-pagination
					v-model:current-page="current"
					v-model:page-size="size"
					:page-sizes="[10, 20, 50]"
					:small="false"
					:background="true"
					layout="total, sizes, prev, pager, next, jumper"
					:total="total" />
			</div>
		</el-card>
	</div>

	<!-- 新增表单 -->
	<el-dialog
		v-model="addVisible"
		title="添加文章分类"
		width="40%"
		:draggable="true"
		:close-on-click-modal="false"
		:close-on-press-escape="false">
		<el-form ref="addCategoryForm" :rules="rules" :model="category">
			<el-form-item label="分类名称" prop="name" label-width="120px">
				<el-input v-model="category.name" placeholder="请输入分类名称"></el-input>
			</el-form-item>
		</el-form>
		<template #footer>
			<span class="dialog-footer">
				<el-button @click="close" :loading="btnLoading">取消</el-button>
				<el-button type="primary" @click="onSubmit" :loading="btnLoading"> 提交 </el-button>
			</span>
		</template>
	</el-dialog>
</template>

<script setup>
	// 引入所需图标
	import { RefreshRight, Search } from "@element-plus/icons-vue"
	import { ref, reactive } from "vue"
	import moment from "moment"
	import { queryCategoryPage, addCategory, deleteCategory } from "@/api/admin/category"
	import { showModel } from "../../composables/utils"

	const tableData = ref([])
	const tableLoading = ref(false)
	const btnLoading = ref(false)
	// 当前页码，给了一个默认值 1
	const current = ref(1)
	// 总数据量，给了个默认值 0
	const total = ref(0)
	// 每页显示的数据量，给了个默认值 10
	const size = ref(10)

	// 分页查询的分类名称
	const searchCategoryName = ref("")
	// 日期
	const pickDate = ref("")
	const startDate = reactive({})
	const endDate = reactive({})
	const shortcuts = [
		{
			text: "最近一周",
			value: () => {
				const end = new Date()
				const start = new Date()
				start.setTime(start.getTime() - 3600 * 1000 * 24 * 7)
				return [start, end]
			},
		},
		{
			text: "最近一个月",
			value: () => {
				const end = new Date()
				const start = new Date()
				start.setTime(start.getTime() - 3600 * 1000 * 24 * 30)
				return [start, end]
			},
		},
		{
			text: "最近三个月",
			value: () => {
				const end = new Date()
				const start = new Date()
				start.setTime(start.getTime() - 3600 * 1000 * 24 * 90)
				return [start, end]
			},
		},
	]
	// 监听日期组件改变事件，并将开始结束时间设置到变量中
	const datepickerChange = (e) => {
		startDate.value = moment(e[0]).format("YYYY-MM-DD")
		endDate.value = moment(e[1]).format("YYYY-MM-DD")
	}

	const getTableData = () => {
		tableLoading.value = true
		queryCategoryPage({
			current: current.value,
			size: size.value,
			createTimeStart: startDate.value ? startDate.value + " 00:00:00" : "",
			createTimeEnd: endDate.value ? endDate.value + " 23:59:59" : "",
			name: searchCategoryName.value,
		})
			.then((res) => {
				if (res.success === true) {
					tableData.value = res.data
					current.value = res.current
					size.value = res.size
					total.value = res.total
				}
			})
			.finally(() => {
				tableLoading.value = false
			})
	}
	getTableData()

	const reset = () => {
		searchCategoryName.value = ""
		pickDate.value = ""
		startDate.value = ""
		endDate.value = ""
		getTableData()
	}

	// 新增
	const addVisible = ref(false)
	const addCategoryForm = ref(null)
	const category = reactive({
		name: "",
	})
	const rules = {
		name: [
			{
				required: true,
				message: "分类名称不能为空",
				trigger: "blur",
			},
			{ min: 1, max: 20, message: "分类名称字数要求大于 1 个字符，小于 20 个字符", trigger: "blur" },
		],
	}
	const onSubmit = () => {
		btnLoading.value = true
		addCategoryForm.value.validate((valid) => {
			if (!valid) {
				ElMessage.error("请完善表单信息")
				return
			}
			addCategory(category).then((res) => {
				if (res.success === true) {
					ElMessage.success("新增成功")
					close()
					getTableData()
				} else {
					ElMessage.error(res.errorMessage)
				}
			}).finally(() => btnLoading.value = false)
		})
	}
	const close = () => {
		addVisible.value = false
		addCategoryForm.value.resetFields()
	}

	// 删除
	const handleDeleteCategory = (row) => {
		console.log(row)
		showModel(`确认删除分类 ${row.name} 吗？`, "warning", "删除确认").then(() => {
			deleteCategory(row.categoryId).then((res) => {
				if (res.success === true) {
					ElMessage.success("删除成功")
					getTableData()
				} else {
					ElMessage.error(res.errorMessage)
				}
			})
		})
	}
</script>
