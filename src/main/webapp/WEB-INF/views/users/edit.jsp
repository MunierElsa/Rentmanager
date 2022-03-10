<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<%@include file="/WEB-INF/views/common/head.jsp"%>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <!-- Left side column. contains the logo and sidebar -->
    <%@ include file="/WEB-INF/views/common/sidebar.jsp" %>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                Utilisateurs
            </h1>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-md-12">
                    <!-- Horizontal Form -->
                    <div class="box">
                        <!-- form start -->
                        <form class="form-horizontal" method="post">
                            <div class="box-body">
                            <div class="form-group">
                                    <label for="id" class="col-sm-2 control-label">Id</label>

									<!-- Pour rï¿½upï¿½rer la valeur rentrï¿½e dans un champ input de cette jsp au niveau de votre servlet -->
									<!-- vous devez passer par les methodes getParameter de l'objet request, est spï¿½cifiant la valeur -->
									<!-- de l'attribut "name" de l'input -->
                                    <div class="col-sm-10">
                                        <select class="form-control" id="id" name="id">
                                            <c:forEach items = "${listUsers }" var="user">
                                				<option value="${user.id}">${user.id} - ${user.prenom} ${user.nom} / ${user.email} / ${user.naissance}</option>
                                			</c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-group"><label class="col-sm-2 control-label">Modifications : </label></div> 
                                <div class="form-group"><p STYLE="padding:0 0 0 100px;">(Remettez la même valeur si vous ne souhaitez pas modifier un des paramètres)</p></div>
                                <div class="form-group">
                                    <label for="last_name" class="col-sm-2 control-label">Nom</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" id="last_name" name="last_name" required minlength="3" placeholder="Nom">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="first_name" class="col-sm-2 control-label">Prenom</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" id="first_name" name="first_name" required minlength="3" placeholder="Prenom">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="email" class="col-sm-2 control-label">Email</label>

                                    <div class="col-sm-10">
                                        <input type="email" class="form-control" id="email" name="email" placeholder="Email">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="naissance" class="col-sm-2 control-label">Date de naissance</label>
                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" id="naissance" name="naissance" required
                                               data-inputmask="'alias': 'yyyy-mm-dd'" data-mask>
                                    </div>
                                </div>
                            </div>
                            <!-- /.box-body -->
                            <div class="box-footer">
                                <button type="submit" class="btn btn-info pull-right" id="EditUsers" href="/user">Modifier</button>
                            </div>
                            <!-- /.box-footer -->
                        </form>
                    </div>
                    <!-- /.box -->
                </div>
                <!-- /.col -->
            </div>
        </section>
        <!-- /.content -->
    </div>

    <%@ include file="/WEB-INF/views/common/footer.jsp" %>
</div>
<!-- ./wrapper -->

<%@ include file="/WEB-INF/views/common/js_imports.jsp" %>
</body>
</html>
