package com.alibaba.druid.sql.dialect.postgresql.ast;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.SQLOrderBy;
import com.alibaba.druid.sql.ast.statement.SQLSelectQueryBlock;
import com.alibaba.druid.sql.dialect.postgresql.visitor.PGASTVisitor;
import com.alibaba.druid.sql.visitor.SQLASTVisitor;

public class PGSelectQueryBlock extends SQLSelectQueryBlock {

	private static final long serialVersionUID = 1L;

	private PGWithClause with;
	private List<SQLExpr> distinctOn = new ArrayList<SQLExpr>(2);
	private SQLExpr limit;
	private SQLExpr offset;
	private WindowClause window;

	private SQLOrderBy orderBy;
	private FetchClause fetch;
	private ForClause forClause;
	private IntoClause into;

	public IntoClause getInto() {
		return into;
	}

	public void setInto(IntoClause into) {
		this.into = into;
	}

	@Override
	protected void accept0(SQLASTVisitor visitor) {
		accept0((PGASTVisitor) visitor);
	}

	protected void accept0(PGASTVisitor visitor) {
		if (visitor.visit(this)) {
			acceptChild(visitor, this.with);
			acceptChild(visitor, this.distinctOn);
			acceptChild(visitor, this.selectList);
			acceptChild(visitor, this.into);
			acceptChild(visitor, this.from);
			acceptChild(visitor, this.where);
			acceptChild(visitor, this.groupBy);
			acceptChild(visitor, this.window);
			acceptChild(visitor, this.orderBy);
			acceptChild(visitor, this.limit);
			acceptChild(visitor, this.offset);
			acceptChild(visitor, this.fetch);
			acceptChild(visitor, this.forClause);
		}
		visitor.endVisit(this);
	}

	public FetchClause getFetch() {
		return fetch;
	}

	public void setFetch(FetchClause fetch) {
		this.fetch = fetch;
	}

	public ForClause getForClause() {
		return forClause;
	}

	public void setForClause(ForClause forClause) {
		this.forClause = forClause;
	}

	public WindowClause getWindow() {
		return window;
	}

	public void setWindow(WindowClause window) {
		this.window = window;
	}

	public PGWithClause getWith() {
		return with;
	}

	public void setWith(PGWithClause with) {
		this.with = with;
	}

	public SQLExpr getLimit() {
		return limit;
	}

	public void setLimit(SQLExpr limit) {
		this.limit = limit;
	}

	public SQLOrderBy getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(SQLOrderBy orderBy) {
		this.orderBy = orderBy;
	}

	public SQLExpr getOffset() {
		return offset;
	}

	public void setOffset(SQLExpr offset) {
		this.offset = offset;
	}

	public List<SQLExpr> getDistinctOn() {
		return distinctOn;
	}

	public void setDistinctOn(List<SQLExpr> distinctOn) {
		this.distinctOn = distinctOn;
	}

	public static class WindowClause extends PGSQLObjectImpl {
		private static final long serialVersionUID = 1L;
		private SQLExpr name;
		private List<SQLExpr> definition = new ArrayList<SQLExpr>(2);

		public SQLExpr getName() {
			return name;
		}

		public void setName(SQLExpr name) {
			this.name = name;
		}

		public List<SQLExpr> getDefinition() {
			return definition;
		}

		public void setDefinition(List<SQLExpr> definition) {
			this.definition = definition;
		}

		@Override
		public void accept0(PGASTVisitor visitor) {
			if (visitor.visit(this)) {
				acceptChild(visitor, name);
				acceptChild(visitor, definition);
			}
		}
	}

	public static class FetchClause extends PGSQLObjectImpl {
		private static final long serialVersionUID = 1L;

		public static enum Option {
			FIRST, NEXT
		}

		private Option option;
		private SQLExpr count;

		public Option getOption() {
			return option;
		}

		public void setOption(Option option) {
			this.option = option;
		}

		public SQLExpr getCount() {
			return count;
		}

		public void setCount(SQLExpr count) {
			this.count = count;
		}

		@Override
		public void accept0(PGASTVisitor visitor) {
			if (visitor.visit(this)) {
				acceptChild(visitor, count);
			}
		}

	}

	public static class ForClause extends PGSQLObjectImpl {
		private static final long serialVersionUID = 1L;

		public static enum Option {
			UPDATE, SHARE
		}

		private List<SQLExpr> of = new ArrayList<SQLExpr>(2);
		private boolean noWait;
		private Option option;

		public Option getOption() {
			return option;
		}

		public void setOption(Option option) {
			this.option = option;
		}

		public List<SQLExpr> getOf() {
			return of;
		}

		public void setOf(List<SQLExpr> of) {
			this.of = of;
		}

		public boolean isNoWait() {
			return noWait;
		}

		public void setNoWait(boolean noWait) {
			this.noWait = noWait;
		}

		@Override
		public void accept0(PGASTVisitor visitor) {
			if (visitor.visit(this)) {
				acceptChild(visitor, of);
			}
		}
	}

	public static class IntoClause extends PGSQLObjectImpl {
		public static enum Option {
			TEMPORARY, TEMP, UNLOGGED
		}

		private static final long serialVersionUID = 1L;

		private SQLExpr table;
		private Option option;

		public Option getOption() {
			return option;
		}

		public void setOption(Option option) {
			this.option = option;
		}

		public SQLExpr getTable() {
			return table;
		}

		public void setTable(SQLExpr table) {
			this.table = table;
		}

		@Override
		public void accept0(PGASTVisitor visitor) {
			if (visitor.visit(this)) {
				acceptChild(visitor, table);
			}
		}

	}
}
