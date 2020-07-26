package model;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

//데이터 베이스에 쿼리를 실행하여 결과를 리턴 받아주는 클래스
public class ShoppingDAO {

	//쿼리를 실행시켜서 데이터를 가져올수 있도록해주는 객체선언 스프링용 (preparedstatement객체와 사용법유사)
	SimpleJdbcTemplate template;

	//데이터 베이스에 접근하여 데이터를 읽어가져올수 있는객체
	DataSource dataSource;

	//설정취득 객체를 생성시키지않고 dataSource사용
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		//객체 생성
		template = new SimpleJdbcTemplate(dataSource);
	}

	//모든 수작업 공구를 리턴해주는 메소드
	public List<SuBean> getAllSutool() {
		//쿼리 준비
		String sql ="select * from sutool";
		//빈클래스(컬럼명과 똑같은 클래스를  선언한 객체)와 컬럼명을 메칭하는 
		RowMapper<SuBean> rm = new BeanPropertyRowMapper<SuBean>(SuBean.class);
		return template.query(sql, rm);
	}

	//선택된 수작업 공구를 리턴해주는 메소드
	public List<SuBean> getSelectSutool(String num) {
		//쿼리 준비
		String sql ="select * from sutool where sucate=?";
		//빈클래스(컬럼명과 똑같은 클래스를  선언한 객체)와 컬럼명을 메칭하는 
		RowMapper<SuBean> rm = new BeanPropertyRowMapper<SuBean>(SuBean.class);
		return template.query(sql, rm, num);
	}

	//하나의 공구정보를 리턴하는 메소드
	public SuBean getOneSutool(int suno) {
		String sql="select * from sutool where suno=?";		
		RowMapper<SuBean> rm = new BeanPropertyRowMapper<SuBean>(SuBean.class);
		return template.queryForObject(sql, rm, suno);
	}
	//아이디가 있는지 파악
	public int getLogin(MemberBean mbean) {
		// 쿼리 준비
		String sql="select count(*) from member where memid=?";
		return template.queryForInt(sql, mbean.getMemid());
	}

	public void insertMember(MemberBean mbean) {
		// 쿼리 작성
		String sql="insert into member values (:memid,:mempasswd1,:mempasswd2,"
				+ ":memname,:memphone,:memdate)";
		//빈클래스가 쿼리문에 1:1맵핑되도록 자동설정
		SqlParameterSource sqlsource = new BeanPropertySqlParameterSource(mbean);
		//쿼리 실행하시오
		template.update(sql, sqlsource);
		
	}

	//로그인 처리시 아이디와 패스워드가 있는지에 대한 여부를 파악
	public int getLoginProc(MemberBean mbean) {
		String sql="select count(*) from member where memid=? and mempasswd1=?";
		return template.queryForInt(sql, mbean.getMemid() , mbean.getMempasswd1());
	}
	
	//게시판의 모든 데이터의 갯수를 리턴
	public int getCount() {
		String sql = "select count(*) from board";
		return template.queryForInt(sql);
	}

	//게시글의 시작 번호와 끝 번호를 기준으로 데이터를 가져오시오
	public List<BoardBean> getAllContent(int startRow, int endRow) {
		String sql = "select * from (select A.*, Rownum Rnum from "
				+ "(select * from board order by ref desc, re_level Asc)A)"
				+ "where Rnum>? and Rnum <=?";
		RowMapper<BoardBean> rm = new BeanPropertyRowMapper<BoardBean>(BoardBean.class);
		
		return template.query(sql, rm, startRow-1 , endRow	);
	}

	//게시글 하나를 저장하는 메소드	
	public void boardInsert(BoardBean bean) {
		int ref = 1; //글그룹
		int re_step=1;//부모글인지 자식글인지
		int re_level=1;//글이 보여질 순서를 저장하는 변수
		int readcount=0;
		
		//글그룹이 현제 얼마인지 알아야 소스를 작성
		String refmax ="select max(ref) from board";
		int refdata = template.queryForInt(refmax);
		if(refdata >= 1 ){
			ref = refdata+1;
		}
		//모든 데이터가 준비 완료되어있기에 데이터를 저장
		bean.setRef(ref);
		bean.setRe_step(re_step);
		bean.setRe_level(re_level);
		bean.setReadcount(readcount);
		
		String sql="insert into board values(board_seq.NEXTVAL,:writer,:email,"
				+ ":subject,:passwd,SYSDATE,:readcount,:ref,:re_step,:re_level,:content)";
		SqlParameterSource sqlsource = new BeanPropertySqlParameterSource(bean);
		//쿼리싱행
		template.update(sql, sqlsource);
		
		
		
	}
	//하나의 게시글을 리턴하는 메소드
	public BoardBean getOneContent(int num) {
		
		//조회수 부터 증가
		String countsql ="update board set readcount=readcount+1 where num=?";
		template.update(countsql, num);
		
		//게시글리턴
		String sql ="select * from board where num=?";
		RowMapper<BoardBean> rm = new BeanPropertyRowMapper<BoardBean>(BoardBean.class);
		return template.queryForObject(sql, rm, num);
	}

	//답글쓰기
	public void reWriteboard(BoardBean bean) {
		//답변글로 인하여 기존에 답변글들의 레벨을 1씩 증가하는 쿼리
		String levelsql="update board set re_level=re_level+1 where ref=? and re_level >?";
		template.update(levelsql, bean.getRef(), bean.getRe_level());
		
		//실제 답변글을 저장하는 메소드
		bean.setRe_step(bean.getRe_step()+1);
		bean.setRe_level(bean.getRe_level()+1);
		
		String sql="insert into board values(board_seq.NEXTVAL,:writer,:email,"
				+ ":subject,:passwd,SYSDATE,:readcount,:ref,:re_step,:re_level,:content)";
		SqlParameterSource sqlsource = new BeanPropertySqlParameterSource(bean);
		//쿼리싱행
		template.update(sql, sqlsource);
	}

	//게시글 수정 
	public void boardUpdate(BoardBean bean) {
		String sql="update board set content=? where num=? and passwd=?";
		template.update(sql, bean.getContent(), bean.getNum(), bean.getPasswd());
	}

	//게시글 삭제
	public void boardDelete(BoardBean bean) {
		String sql="delete from board where num=? and passwd=?";
		template.update(sql, bean.getNum(), bean.getPasswd());
		
	}

	


}








